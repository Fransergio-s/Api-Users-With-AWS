package compasso.com.br.apiuser.service;

import compasso.com.br.apiuser.model.dto.AddressDto;
import compasso.com.br.apiuser.model.dto.UserRequestDto;
import compasso.com.br.apiuser.model.dto.UserResponseDto;
import compasso.com.br.apiuser.model.dto.UserUpdatePasswordDto;
import compasso.com.br.apiuser.model.dto.mapper.AddressMapper;
import compasso.com.br.apiuser.model.dto.mapper.UserMapper;
import compasso.com.br.apiuser.model.entity.User;
import compasso.com.br.apiuser.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CepService cepService;
    private final AddressMapper addressMapper;

    public UserResponseDto create(UserRequestDto user) {
        User newUser = userMapper.toUser(user);
        AddressDto address = cepService.getAddressPerCep(user.getZipCode());
        newUser.setAddress(addressMapper.toAddress(address));
        userRepository.save(newUser);
        return userMapper.toResponseDto(newUser);
    }

    @Transactional
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
