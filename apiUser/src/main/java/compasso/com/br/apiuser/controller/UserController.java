package compasso.com.br.apiuser.controller;

import compasso.com.br.apiuser.model.dto.UserRequestDto;
import compasso.com.br.apiuser.model.dto.UserResponseDto;
import compasso.com.br.apiuser.model.dto.UserUpdatePasswordDto;
import compasso.com.br.apiuser.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto user) {
       return ResponseEntity.ok().body(userService.create(user));
    }

    @PatchMapping("/update-password")
    public ResponseEntity<UserResponseDto> update(@RequestBody UserUpdatePasswordDto user) {
        userService.updatePassword(user);
        return ResponseEntity.noContent().build();
    }

}
