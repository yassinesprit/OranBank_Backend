package com.bfi.orabank.Services;

import com.bfi.orabank.Entities.Notification;
import com.bfi.orabank.Entities.StatusNotification;
import com.bfi.orabank.Repositories.NotificationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Notification> getUnreadNotificationsByAlias(String alias) {
        List<Notification> notifications=notificationRepository.findByStatusAndDestinataireAliasOrExpediteurAlias(StatusNotification.nonLu,alias,alias );
        List<Notification> notificationsFiltred = new ArrayList<>();
        for (Notification notification:notifications) {
            if (notification.getStatus() == StatusNotification.nonLu){
                notificationsFiltred.add(notification);
            }
        }
        return notificationsFiltred;
    }

    @Override
    @Transactional
    public Notification markNotificationAsRead(int notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + notificationId));

        notification.setStatus(StatusNotification.lu);
        return notificationRepository.save(notification);
    }

    @Override
    public void supprimerNotification(int notificationId) {
        notificationRepository.deleteById(notificationId);
    }
}
