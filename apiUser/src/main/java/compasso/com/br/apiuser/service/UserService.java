package compasso.com.br.apiuser.service;

import compasso.com.br.apiuser.model.dto.UserRequestDto;
import compasso.com.br.apiuser.model.dto.UserResponseDto;
import compasso.com.br.apiuser.model.dto.UserUpdatePasswordDto;
import compasso.com.br.apiuser.model.dto.mapper.UserMapper;
import compasso.com.br.apiuser.model.entity.User;
import compasso.com.br.apiuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDto create(UserRequestDto user) {
        User newUser = userMapper.toUser(user);
        userRepository.save(newUser);
        return userMapper.toResponseDto(newUser);
    }

    public void updatePassword(UserUpdatePasswordDto user) {
        User newUser = userRepository.findByUsername(user.getUsername());
        if (newUser == null) {
            throw new RuntimeException("User not found");
        }
        if (!newUser.getPassword().equals(user.getNewPassword())) {
            throw new RuntimeException("New password doesn't match");
        }
        userRepository.save(newUser);
    }
}
