package com.oryanhosh.andronotifier;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;

public class AndroNotifier {
    static final String NOTIFICATIONS_DATA = "simple_notifyer_data";

    public static void addNotification(Context context, AndroNotification notification)
    {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString(NOTIFICATIONS_DATA, "");
        AndroNotifierData data;

        if (json != null && !json.isEmpty())
        {
            data = gson.fromJson(json, AndroNotifierData.class);
        }
        else
        {
            data = new AndroNotifierData();
        }

        data.addNotification(notification);

        setNewNotifyer(context, data);

        saveDataToSharedPreferences(context, data);
    }

    public static void removeNotification(Context context, String notificationID)
    {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString(NOTIFICATIONS_DATA, "");
        if(json != null && !json.isEmpty())
        {
            AndroNotifierData data = gson.fromJson(json, AndroNotifierData.class);
            data.removeNotification(notificationID);

            setNewNotifyer(context, data);

            saveDataToSharedPreferences(context, data);
        }
    }

    public static ArrayList<AndroNotification> getNotifications(Context context)
    {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString(NOTIFICATIONS_DATA, "");

        if(json != null && !json.isEmpty())
        {
            AndroNotifierData data = gson.fromJson(json, AndroNotifierData.class);
            return data.getNotifications();
        }
        return new ArrayList<>();
    }

    static SharedPreferences getSharedPreferences(Context context)
    {
        return context.getSharedPreferences(NOTIFICATIONS_DATA, Context.MODE_PRIVATE);
    }

    static void setNewNotifyer(Context context, AndroNotifierData data)
    {
        if(!data.getNotifications().isEmpty())
        {
            Intent notifyIntent = new Intent(context,NotifierReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast
                    (context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, getMinNotification(data.getNotifications()).getWhen(), pendingIntent);
        }
    }

    static void saveDataToSharedPreferences(Context context, AndroNotifierData data)
    {
        Gson gson = new Gson();
        SharedPreferences.Editor prefsEditor = getSharedPreferences(context).edit();
        String json = gson.toJson(data);
        prefsEditor.putString(NOTIFICATIONS_DATA, json);
        prefsEditor.apply();
    }

    static AndroNotification getMinNotification(ArrayList<AndroNotification> notifications)
    {
        long min = notifications.get(0).getWhen();
        AndroNotification notification = notifications.get(0);
        for(AndroNotification n : notifications)
        {
            if(min > n.getWhen())
            {
                notification = n;
                min = n.getWhen();
            }
        }
        return notification;
    }
}
