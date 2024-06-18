package com.bfi.orabank.Repositories;

import com.bfi.orabank.Entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
}
