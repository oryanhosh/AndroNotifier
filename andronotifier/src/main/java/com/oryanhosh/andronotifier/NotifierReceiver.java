package com.oryanhosh.andronotifier;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.google.gson.Gson;

public class NotifierReceiver extends BroadcastReceiver {
    final String NOTIFICATION_CHANNEL_ID = "channel_id_01";
    final String NOTIFICATION_NAME = "simpleNotifyerNotification";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Gson gson = new Gson();
        String json = AndroNotifier.getSharedPreferences(context).getString(AndroNotifier.NOTIFICATIONS_DATA, "");
        if(json == null || json.isEmpty())
        {
            return;
        }

        AndroNotifierData data = gson.fromJson(json, AndroNotifierData.class);

        if(intent!= null && intent.getAction() != null && intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            AndroNotifier.setNewNotifyer(context,data);
        }
        else
        {
            AndroNotification minNotification = AndroNotifier.getMinNotification(data.getNotifications());

            executeNotification(context, minNotification);

            data.removeNotification(minNotification.getId());

            AndroNotifier.setNewNotifyer(context,data);

            AndroNotifier.saveDataToSharedPreferences(context, data);
        }
    }

    private void executeNotification(Context context, AndroNotification notification)
    {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = createNotificationBuilder(context, notification);
        notificationManager.notify(0, builder.build());
    }


    private NotificationCompat.Builder createNotificationBuilder(Context context, AndroNotification notification)
    {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(notification.getSmallIcon())
                .setContentTitle(notification.getContentTitle())
                .setContentText(notification.getContentText())
                .setContentInfo(notification.getContentInfo())
                .setContentIntent(PendingIntent.getActivity(context, 0, buildNotificationIntent(context, notification),
                        PendingIntent.FLAG_UPDATE_CURRENT));
        return notificationBuilder;
    }


    private Intent buildNotificationIntent(Context context, AndroNotification notification)
    {
        Intent intent = new Intent();
        if(!notification.getIntentClassName().isEmpty())
        {
            try {
                Class c = context.getClassLoader().loadClass(notification.getIntentClassName());
                if(c != null)
                {
                    intent = new Intent(context, c);
                    if(!notification.getIntentExtraStringKey().isEmpty())
                    {
                        intent.putExtra(notification.getIntentExtraStringKey(), notification.getIntentExtraStringValue());
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return intent;
    }
}
