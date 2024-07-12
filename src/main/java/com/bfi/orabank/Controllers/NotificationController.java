package com.bfi.orabank.Controllers;

import com.bfi.orabank.DTO.NotificationRequest;
import com.bfi.orabank.Entities.Notification;
import com.bfi.orabank.Services.INotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/unread/{alias}")
    public List<Notification> getUnreadNotificationsByAlias(@PathVariable String alias) {
        return notificationService.getUnreadNotificationsByAlias(alias);
    }

    @PostMapping("/markread/{notificationId}")
    public ResponseEntity<Notification> markNotificationAsRead(@PathVariable("notificationId") int notificationId) {
        Notification updatedNotification = notificationService.markNotificationAsRead(notificationId);
        return new ResponseEntity<>(updatedNotification, HttpStatus.OK);
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Object> supprimerNotification(@PathVariable("notificationId") int notificationId) {
         notificationService.supprimerNotification(notificationId);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}


