package compasso.com.br.apiuser.model.dto.mapper;

import compasso.com.br.apiuser.model.dto.UserRequestDto;
import compasso.com.br.apiuser.model.dto.UserResponseDto;
import compasso.com.br.apiuser.model.entity.Address;
import compasso.com.br.apiuser.model.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private AddressMapper addressMapper;

    public UserMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }


    public UserResponseDto toResponseDto(User user, Address address) {
        return new UserResponseDto(
                user.getUsername(), user.getEmail(), addressMapper.toDto(address));
    }
    public User toUser(UserRequestDto userRequestDto) {
        return new User(
                userRequestDto.username(), userRequestDto.password(), userRequestDto.email());
    }

}
