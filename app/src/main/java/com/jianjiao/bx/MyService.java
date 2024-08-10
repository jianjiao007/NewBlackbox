package com.jianjiao.bx;

import static com.jianjiao.bx.node.AccUtils.printLogMsg;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;

import com.jianjiao.bx.node.GlobalVariableHolder;

public class MyService extends Service {
    private static final String TAG = GlobalVariableHolder.tag;
    private static final String CHANNEL_ID = "com.primedu.cn";
    private static final String CHANNEL_NAME = "Channel One";
    private static final int NOTIFICATION_ID = 1;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            printLogMsg("提高进程优先级", 0);

            createNotificationChannel();
            showNotification();

            return START_STICKY;
        } catch (Exception e) {
            // 更具体的错误处理
            e.printStackTrace();
        }
        return 1;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setShowBadge(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private void showNotification() {
        Intent notificationIntent = new Intent(this, getClass());
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        Notification.Builder builder = new Notification.Builder(this, CHANNEL_ID)
                .setTicker("Nature")
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo))
                .setContentTitle("FATJS正在运行中")
                .setContentIntent(pendingIntent)
                .setContentText("MyService is running......")
                .setPriority(Notification.PRIORITY_HIGH);

        Notification notification = builder.build();
        notification.defaults = Notification.DEFAULT_SOUND;

        startForeground(NOTIFICATION_ID, notification);
    }
}
