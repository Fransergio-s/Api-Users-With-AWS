package compasso.com.br.apiuser.service;

import compasso.com.br.apiuser.model.dto.AddressDto;
import compasso.com.br.apiuser.model.dto.UserRequestDto;
import compasso.com.br.apiuser.model.dto.UserResponseDto;
import compasso.com.br.apiuser.model.dto.UserUpdatePasswordDto;
import compasso.com.br.apiuser.model.dto.mapper.AddressMapper;
import compasso.com.br.apiuser.model.dto.mapper.UserMapper;
import compasso.com.br.apiuser.model.entity.Address;
import compasso.com.br.apiuser.model.entity.User;
import compasso.com.br.apiuser.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service

public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CepService cepService;
    private final AddressMapper addressMapper;
    private final AddressService  addressService;

    public UserService(UserRepository userRepository, UserMapper userMapper, CepService cepService, AddressMapper addressMapper, AddressService addressService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.cepService = cepService;
        this.addressMapper = addressMapper;
        this.addressService = addressService;
    }


    public UserResponseDto create(UserRequestDto user) {
        System.out.println("Vendo o user: " + user);
        Address address = addressService.create(
                cepService.getAddressPerCep(user.getZipCode()));

        User newUser = userMapper.toUser(user);
        newUser.setAddress(address);
        userRepository.save(newUser);
        return userMapper.toResponseDto(newUser);
    }

    @Transactional
    public void updatePassword(UserUpdatePasswordDto user) {
        User newUser = userRepository.findByUsername(user.getUsername());
        System.out.println("Vendo o user: " + newUser);

        if (newUser == null) {
            throw new RuntimeException("User not found");
        }
        if (!user.getOldPassword().equals(newUser.getPassword())) {
            throw new RuntimeException("Old password don't match");
        }
        userRepository.save(newUser);
    }
}
