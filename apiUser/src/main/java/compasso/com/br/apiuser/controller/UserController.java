package compasso.com.br.apiuser.controller;

import compasso.com.br.apiuser.model.dto.UserRequestDto;
import compasso.com.br.apiuser.model.dto.UserResponseDto;
import compasso.com.br.apiuser.model.dto.UserUpdatePasswordDto;
import compasso.com.br.apiuser.producer.NotificationProducer;
import compasso.com.br.apiuser.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final NotificationProducer notificationProducer; // Declaração do NotificationProducer

    public UserController(UserService userService, NotificationProducer notificationProducer) {
        this.userService = userService;
        this.notificationProducer = notificationProducer; // Injeção de dependência
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto user) {
        UserResponseDto response = userService.create(user);
        // Enviando notificação de registro
        notificationProducer.sendNotification(user.getUsername(), "REGISTER");
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update-password")
    public ResponseEntity<UserResponseDto> update(@RequestBody UserUpdatePasswordDto user) {
        userService.updatePassword(user);
        // Enviando notificação de atualização de senha
        notificationProducer.sendNotification(user.getUsername(), "UPDATE_PASSWORD");
        return ResponseEntity.noContent().build();
    }

}
