package com.Ilker.notification_service.repository;

import com.Ilker.notification_service.entitiy.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
