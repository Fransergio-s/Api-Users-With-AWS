package compasso.com.br.apiuser.producer;

import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;


@Service
public class NotificationProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(String username, String operation) {
        String message = String.format("User: %s, Operation: %s", username, operation);
        kafkaTemplate.send("notify-topic", message);
    }

}
