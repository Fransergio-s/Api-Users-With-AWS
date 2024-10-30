package br.com.compasso.notifyapi.consumer;

import br.com.compasso.notifyapi.entity.Notification;
import br.com.compasso.notifyapi.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    public NotificationConsumer(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @KafkaListener(topics = "notify-topic", groupId = "notify_group")
    public void listen(String message) {
        // Aqui você pode salvar no MongoDB
        System.out.println("Received message: " + message);
        notificationRepository.save(new Notification(message));
    }
}
