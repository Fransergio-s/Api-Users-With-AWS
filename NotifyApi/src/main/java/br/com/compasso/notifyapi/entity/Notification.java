package br.com.compasso.notifyapi.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "notify")
public class Notification {
    @Id
    private String id;
    private String message;

    public Notification(String message) {
        this.message = message;
    }
}
