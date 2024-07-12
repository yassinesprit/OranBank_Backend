package com.bfi.orabank.Repositories;

import com.bfi.orabank.Entities.Notification;
import com.bfi.orabank.Entities.StatusNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {

    List<Notification> findByStatusAndDestinataireAliasOrExpediteurAlias( StatusNotification s,String expediteurAlias, String destinataireAlias);

}
