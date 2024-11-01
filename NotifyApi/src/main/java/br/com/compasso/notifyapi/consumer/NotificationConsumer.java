package br.com.compasso.notifyapi.consumer;
import br.com.compasso.notifyapi.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import br.com.compasso.notifyapi.entity.Notification;


@Slf4j
@Service
public class NotificationConsumer {

    @Autowired
    private NotificationRepository notificationRepository;

    @KafkaListener(topics = "notify-topic", groupId = "notify_group")
    public void listen(String message) {
        log.warn("Received message: " + message);
        Notification notification = new Notification(message);

        notificationRepository.save(notification);
    }
}
