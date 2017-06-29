package com.darby.joe.gas.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import com.darby.joe.gas.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            String msgString = remoteMessage.getNotification().getBody();

            Notification notification = new Notification.Builder(this)
                    .setContentText(msgString)
                    .setContentTitle("Firebase Notification")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true)
                    .build();

            NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            nManager.notify(0, notification);
        }
    }
}
