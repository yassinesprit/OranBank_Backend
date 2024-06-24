package com.bfi.orabank.Services;

import com.bfi.orabank.Entities.Notification;

import java.util.List;

public interface INotificationService {
    public List<Notification> getAllNotifications();
    public Notification save(Notification notification, List<String> usernames);
}
