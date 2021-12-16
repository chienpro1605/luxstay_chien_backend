package com.codegym.luxstay.service.iservice;

import com.codegym.luxstay.model.Notification;
import com.codegym.luxstay.service.IGeneralService;

public interface INotification extends IGeneralService<Notification> {
    Iterable<Notification> findAllByUser(long userId);
}
