package compasso.com.br.apiuser.controller;


import compasso.com.br.apiuser.model.dto.LoginRequest;
import compasso.com.br.apiuser.model.dto.LoginResponse;
import compasso.com.br.apiuser.producer.NotificationProducer;
import compasso.com.br.apiuser.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "tokenAuth")
@Tag(name = "Login", description = "Log in to the application based on a valid username and password")
@RestController
@Slf4j
public class LoginController {

    private final LoginService service;
    private final NotificationProducer notificationProducer;

    public LoginController(LoginService service, NotificationProducer notificationProducer) {
        this.service = service;
        this.notificationProducer = notificationProducer;
    }

    @Operation(summary = "Create a token based on a valid username and password",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok->Created token.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = LoginResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Not Found: No user found based on this username.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Error->token not created.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = LoginResponse.class)))
            })
    @SecurityRequirement(name = "")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = service.login(request);
        notificationProducer.sendNotification(request.username(), "LOGIN");
        return ResponseEntity.ok().body(response);
    }

}
