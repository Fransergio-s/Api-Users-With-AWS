package compasso.com.br.apiuser.service;

import compasso.com.br.apiuser.model.dto.UserRequestDto;
import compasso.com.br.apiuser.model.dto.UserResponseDto;
import compasso.com.br.apiuser.model.dto.UserUpdatePasswordDto;

public interface UserService {
    UserResponseDto create(UserRequestDto user);
    void updatePassword(UserUpdatePasswordDto user);
}
