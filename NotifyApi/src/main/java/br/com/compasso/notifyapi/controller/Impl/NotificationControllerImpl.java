package br.com.compasso.notifyapi.controller.Impl;

import br.com.compasso.notifyapi.controller.NotificationController;
import br.com.compasso.notifyapi.entity.Notification;
import br.com.compasso.notifyapi.repository.NotificationRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationControllerImpl implements NotificationController    {
    private final NotificationRepository notificationRepository;

    public NotificationControllerImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getAll(){
        return notificationRepository.findAll();
    }

}