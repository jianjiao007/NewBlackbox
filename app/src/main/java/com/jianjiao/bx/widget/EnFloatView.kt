package com.jianjiao.bx.widget

import android.content.Context
import android.view.MotionEvent
import com.imuxuan.floatingview.FloatingMagnetView
import com.jianjiao.bx.R

class EnFloatView(mContext: Context) : FloatingMagnetView(mContext) {
    private var rockerView: com.jianjiao.bx.widget.RockerView? = null

    init {
        inflate(mContext, R.layout.view_float_rocker, this)
        initRockerView()
    }

    private fun initRockerView() {
        rockerView = findViewById(R.id.rocker)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            rockerView?.setCanMove(false)
        } else if (event?.action == MotionEvent.ACTION_UP) {
            rockerView?.setCanMove(true)
        }
        return super.onTouchEvent(event)
    }
}