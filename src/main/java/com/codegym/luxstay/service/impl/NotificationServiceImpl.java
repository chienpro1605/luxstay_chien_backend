package com.codegym.luxstay.service.impl;

import com.codegym.luxstay.model.Notification;
import com.codegym.luxstay.repository.INotificationRepository;
import com.codegym.luxstay.service.iservice.INotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationServiceImpl implements INotification {
    @Autowired
    private INotificationRepository notificationRepository;

    @Override
    public Iterable<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> findById(long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void delete(long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public Iterable<Notification> findAllByUser(long userId) {
        return notificationRepository.findAllByUser(userId);
    }
}
