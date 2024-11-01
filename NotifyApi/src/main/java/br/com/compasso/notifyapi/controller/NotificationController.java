package br.com.compasso.notifyapi.controller;

import br.com.compasso.notifyapi.entity.Notification;
import br.com.compasso.notifyapi.repository.NotificationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @GetMapping
    public List<Notification> getAll(){
        return notificationRepository.findAll();
    }

}