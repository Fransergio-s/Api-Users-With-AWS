package br.com.compasso.notifyapi.controller;

import br.com.compasso.notifyapi.entity.Notification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public interface NotificationController {

    @GetMapping
    List<Notification> getAll();
}
