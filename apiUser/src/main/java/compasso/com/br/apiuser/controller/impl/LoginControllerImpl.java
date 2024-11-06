package compasso.com.br.apiuser.controller.impl;


import compasso.com.br.apiuser.controller.LoginController;
import compasso.com.br.apiuser.model.dto.LoginRequest;
import compasso.com.br.apiuser.model.dto.LoginResponse;
import compasso.com.br.apiuser.producer.NotificationProducer;

import compasso.com.br.apiuser.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginControllerImpl implements LoginController {

    private final LoginService service;
    private final NotificationProducer notificationProducer;

    public LoginControllerImpl(LoginService service, NotificationProducer notificationProducer) {
        this.service = service;
        this.notificationProducer = notificationProducer;
    }

    @Override
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = service.login(request);
        notificationProducer.sendNotification(request.username(), "LOGIN");
        return ResponseEntity.ok().body(response);
    }

}
