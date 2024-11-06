package compasso.com.br.apiuser.controller.impl;

import compasso.com.br.apiuser.controller.UserController;
import compasso.com.br.apiuser.model.dto.UserRequestDto;
import compasso.com.br.apiuser.model.dto.UserResponseDto;
import compasso.com.br.apiuser.model.dto.UserUpdatePasswordDto;
import compasso.com.br.apiuser.producer.NotificationProducer;
import compasso.com.br.apiuser.service.UserService;
import compasso.com.br.apiuser.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final NotificationProducer notificationProducer;

    public UserControllerImpl(UserServiceImpl userService, NotificationProducer notificationProducer) {
        this.userService = userService;
        this.notificationProducer = notificationProducer;
    }

    @Override
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto user) {
        UserResponseDto response = userService.create(user);
        notificationProducer.sendNotification(user.username(), "REGISTER");
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<UserResponseDto> update(@RequestBody UserUpdatePasswordDto user) {
        userService.updatePassword(user);
        notificationProducer.sendNotification(user.username(), "UPDATE_PASSWORD");
        return ResponseEntity.noContent().build();
    }

}
