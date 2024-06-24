package com.bfi.orabank.Controllers;

import com.bfi.orabank.DTO.NotificationRequest;
import com.bfi.orabank.Entities.Notification;
import com.bfi.orabank.Services.INotificationService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
    private final INotificationService notificationService;

    @PostMapping
    public Notification createNotification(@RequestBody NotificationRequest notificationRequest) {
        return notificationService.save(notificationRequest.getNotification(), notificationRequest.getUsernames());
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }
}


/*@PostMapping("/notify")
    public void notifyClient(@RequestBody Notification notification) {
        template.convertAndSend("/topic/notifications", notification);
    }*/

    /*@MessageMapping("/application")
    @SendTo("/all/messages")
    public Notification send(final Notification message) throws Exception {
        return message;
    }*/
