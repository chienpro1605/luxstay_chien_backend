package com.codegym.luxstay.controller;

import com.codegym.luxstay.model.Notification;
import com.codegym.luxstay.service.iservice.INotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationControllerAPI {
    @Autowired
    private INotification notificationService;

    @GetMapping("/findAllByUser/{userId}")
    public ResponseEntity<Iterable<Notification>> findAllByUser(@PathVariable long userId){
        return new ResponseEntity<>(notificationService.findAllByUser(userId), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Notification> save(@RequestBody Notification notification) {
        return new ResponseEntity<>(notificationService.save(notification),HttpStatus.CREATED);
    }
}
