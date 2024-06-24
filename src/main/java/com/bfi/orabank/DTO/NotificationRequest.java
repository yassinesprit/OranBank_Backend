package com.bfi.orabank.DTO;

import com.bfi.orabank.Entities.Notification;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NotificationRequest {
    Notification notification;
    List<String> usernames;
}
