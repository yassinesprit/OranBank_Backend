package com.bfi.orabank.Services;

import com.bfi.orabank.Entities.Notification;

import java.util.List;

public interface INotificationService {
    public List<Notification> getAllNotifications();
    public Notification save(Notification notification, List<String> usernames);
    public List<Notification> getUnreadNotificationsByAlias(String alias) ;
    public Notification markNotificationAsRead(int notificationId);
    public void supprimerNotification(int notificationId);
}
