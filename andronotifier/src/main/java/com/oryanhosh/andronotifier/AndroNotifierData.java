package com.oryanhosh.andronotifier;

import java.util.ArrayList;

class AndroNotifierData {
    private ArrayList<AndroNotification> notifications;

    AndroNotifierData()
    {
        notifications = new ArrayList<>();
    }

    ArrayList<AndroNotification> getNotifications()
    {
        return notifications;
    }

    void removeNotification(String notificationID)
    {
        for (AndroNotification n : notifications)
        {
            if(n.getId().equals(notificationID))
            {
                notifications.remove(n);
                break;
            }
        }
    }

    void addNotification(AndroNotification notification)
    {
        for(AndroNotification n:notifications)
        {
            if(n.getId().equals(notification.getId()))
            {
                notifications.remove(n);
                break;
            }
        }
        notifications.add(notification);
    }
}
