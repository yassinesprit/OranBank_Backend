package com.bfi.orabank.Services;

import com.bfi.orabank.Entities.Notification;
import com.bfi.orabank.Repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements INotificationService{
    NotificationRepository notificationRepository;
    private SimpMessagingTemplate template;


    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification save(Notification notification, List<String> usernames) {
        Notification savedNotification = notificationRepository.save(notification);
        for (String username : usernames) {
            template.convertAndSendToUser(username, "/queue/notifications", savedNotification);
        }
        return savedNotification;
    }
}
