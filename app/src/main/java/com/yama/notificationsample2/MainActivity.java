package com.yama.notificationsample2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        createNotificationChannel ();
        btn=findViewById (R.id.btn_noti);
        btn.setOnClickListener (view -> {
            createNotification ();
        });
    }
    //노티피케이션 채널 생성
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            String channelId = getString (R.string.notification_channel_id);
            String channelName = getString (R.string.notification_channel_name);
            String channelDes = getString (R.string.notification_channel_description);
            NotificationManager notificationManager = (NotificationManager) getSystemService (Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel =
                    new NotificationChannel (channelId //채널 ID
                            , channelName //채널 Name
                            , NotificationManager.IMPORTANCE_HIGH);//중요도 HIGH 부터 헤드업 알림
            notificationChannel.setDescription (channelDes);//채널설명
            notificationManager.createNotificationChannel (notificationChannel);
        }
    }
    //노티피케이션 생성
    private void createNotification(){
        NotificationCompat.Builder notification =
                new NotificationCompat.Builder (this, this.getResources ().getString (R.string.notification_channel_id))
                        .setSmallIcon (R.mipmap.ic_launcher)  //아이콘
                        .setContentTitle ("노티피케이션 테스트") //노티피케이션 타이틀
                        .setContentText ("노티피케이션 테스트 중입니다.") //본문 텍스트
                        .setAutoCancel (true); //사용자가 탭하면 자동으로 알림을 삭제

        NotificationManager notificationManager = (NotificationManager) getSystemService (Context.NOTIFICATION_SERVICE);
        notificationManager.notify (0, notification.build ());
    }
}