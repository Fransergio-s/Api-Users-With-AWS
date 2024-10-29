package compasso.com.br.apiuser.model.dto.mapper;

import compasso.com.br.apiuser.model.dto.UserRequestDto;
import compasso.com.br.apiuser.model.dto.UserResponseDto;
import compasso.com.br.apiuser.model.dto.UserUpdatePasswordDto;
import compasso.com.br.apiuser.model.entity.User;

public class UserMapper {

    public UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(
                user.getUsername(), user.getEmail(), user.getCep());
    }

    public User toUser(UserRequestDto userRequestDto) {
        return new User(
                userRequestDto.getUsername(), userRequestDto.getPassword(), userRequestDto.getEmail(), userRequestDto.getCep());
    }

}
