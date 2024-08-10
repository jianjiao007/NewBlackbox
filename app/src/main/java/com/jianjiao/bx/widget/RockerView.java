package com.jianjiao.bx.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import com.jianjiao.bx.util.MathUtil;

public class RockerView extends SurfaceView implements Runnable, SurfaceHolder.Callback {
    private static final int DEFAULT_AREA_RADIUS = 100;
    private static final int DEFAULT_ROCKER_RADIUS = 35;

    private static final int DEFAULT_REFRESH_CYCLE = 30;
    private static final int DEFAULT_CALLBACK_CYCLE = 300;

    private SurfaceHolder mHolder;
    private static boolean mDrawOk = true;
    private static boolean mCallbackOk = true;

    /**
     * The rocker active area center position.
     * usually, it is the center of this view.
     */
    private Point mAreaPosition;

    /**
     * The Rocker position.
     * usually, it as same asmAreaPosition .
     * if this view touched, it will follow the touch position.
     * <p/>
     * we get position information from this.
     */
    private Point mRockerPosition;

    private int mAreaRadius = -1;
    private int mRockerRadius = -1;

    private boolean canMove = true;

    private final int mCallbackCycle = DEFAULT_CALLBACK_CYCLE;

    public RockerView(Context context) {
        this(context, null);
    }

    public RockerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RockerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // init attrs
        initAttrs();
        // set paint
        setPaint();

        if (isInEditMode()) {
            return;
        }

        // config surfaceView
        configSurfaceView();
        // config surfaceHolder
        configSurfaceHolder();
    }

    private void initAttrs() {
        mAreaRadius = DEFAULT_AREA_RADIUS;
        mRockerRadius = DEFAULT_ROCKER_RADIUS;
    }

    private void setPaint() {
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    private void configSurfaceView() {
        setKeepScreenOn(true); // do not lock screen when surfaceView is running.
        setFocusable(true); // make sure this surfaceView can get focus from keyboard.
        setFocusableInTouchMode(true); // make sure this surfaceView can get focus from touch.
        setZOrderOnTop(true); // make sure this surface is placed on top of the window
    }

    private void configSurfaceHolder() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setFormat(PixelFormat.TRANSPARENT); // 设置背景透明
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth, measureHeight;
        int defaultWidth = (mAreaRadius + mRockerRadius) * 2;
        int defaultHeight = (mAreaRadius + mRockerRadius) / 2;

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);      // 取出宽度的确切数值
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);      // 取出宽度的测量模式

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);    // 取出高度的确切数值
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);    // 取出高度的测量模式

        if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.UNSPECIFIED || widthSize < 0) {
            measureWidth = defaultWidth;
        } else {
            measureWidth = widthSize;
        }

        if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED || heightSize < 0) {
            measureHeight = defaultHeight;
        } else {
            measureHeight = heightSize;
        }
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldWidth, int oldHeight) {
        super.onSizeChanged(w, h, oldWidth, oldHeight);
        mAreaPosition = new Point(w / 2, h / 2);
        mRockerPosition = new Point(mAreaPosition);

        // this need subtract the view padding
        int tempRadius = Math.min(w - getPaddingLeft() - getPaddingRight(), h - getPaddingTop() - getPaddingBottom());
        tempRadius /= 2;
        if (mAreaRadius == -1) {
            mAreaRadius = (int) (tempRadius * 0.75);
        }

        if (mRockerRadius == -1) {
            mRockerRadius = (int) (tempRadius * 0.25);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            Thread mDrawThread = new Thread(this);
            mDrawThread.start();
            // listener callback
            Thread mCallbackThread = new Thread(() -> {
                while (mCallbackOk) {
                    try {
                        Thread.sleep(mCallbackCycle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            mCallbackThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mDrawOk = false;
        mCallbackOk = false;
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            mDrawOk = true;
            mCallbackOk = true;
        } else {
            mDrawOk = false;
            mCallbackOk = false;
        }
    }

    /*Event Response*******************************************************************************/
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        try {
            int len = MathUtil.Companion.getDistance(mAreaPosition.x, mAreaPosition.y, event.getX(), event.getY());
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // 如果屏幕接触点不在摇杆挥动范围内,则不处理
                if (len > mAreaRadius) {
                    return true;
                }
            }

            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (len <= mAreaRadius) {
                    // 如果手指在摇杆活动范围内，则摇杆处于手指触摸位置
                    mRockerPosition.set((int) event.getX(), (int) event.getY());
                } else {
                    // 设置摇杆位置，使其处于手指触摸方向的 摇杆活动范围边缘
                    mRockerPosition = MathUtil.Companion.getPointByCutLength(mAreaPosition,
                            new Point((int) event.getX(), (int) event.getY()), mAreaRadius);
                }
            }
            // 如果手指离开屏幕，则摇杆返回初始位置
            if (event.getAction() == MotionEvent.ACTION_UP) {
                mRockerPosition = new Point(mAreaPosition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void run() {
        if (isInEditMode()) {
            return;
        }

        Canvas canvas = null;
        while (mDrawOk) {
            boolean canMove = this.canMove;
            try {
                if (canMove) {
                    canvas = mHolder.lockCanvas();
                    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                }
                Thread.sleep(DEFAULT_REFRESH_CYCLE); // 休眠
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null && canMove) {
                    mHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    // for preview
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        if (isInEditMode()) {
            canvas.drawColor(Color.WHITE);
        }
    }

    public void setCanMove(boolean isMove) {
        this.canMove = isMove;
    }
}
