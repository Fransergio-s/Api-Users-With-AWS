package compasso.com.br.apiuser.service;

import compasso.com.br.apiuser.exceptions.*;
import compasso.com.br.apiuser.model.dto.UserRequestDto;
import compasso.com.br.apiuser.model.dto.UserResponseDto;
import compasso.com.br.apiuser.model.dto.UserUpdatePasswordDto;
import compasso.com.br.apiuser.model.dto.mapper.UserMapper;
import compasso.com.br.apiuser.model.entity.Address;
import compasso.com.br.apiuser.model.entity.User;
import compasso.com.br.apiuser.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CepService cepService;
    private final AddressService  addressService;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, UserMapper userMapper, CepService cepService, AddressService addressService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.cepService = cepService;
        this.addressService = addressService;
        this.passwordEncoder = passwordEncoder;
    }


    public UserResponseDto create(UserRequestDto user) {
        try {
            Optional<User> test = userRepository.findByUsername(user.getUsername());
            if (test.isPresent()) {
                throw new UserAlreadyExistException("User already exist");
            }
            Address address = addressService.create(
                    cepService.getAddressPerCep(user.getZipCode()));

            User newUser = userMapper.toUser(user);
            newUser.setAddress(address);
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(newUser);
            return userMapper.toResponseDto(newUser);
        }catch (UserCreateException e) {
            throw new UserCreateException();
        }
    }

    @Transactional
    public void updatePassword(UserUpdatePasswordDto user) {
        try {
            Optional<User> newUser = userRepository.findByUsername(user.getUsername());
            if (newUser.isEmpty()) {
                throw new UserNotFoundException("User not found");
            }
            if (passwordEncoder.matches(newUser.get().getPassword(),user.getOldPassword())){
                throw new UserPasswordNotMatch("Old password don't match");
            }
            newUser.get().setPassword(passwordEncoder.encode(user.getNewPassword()));
            userRepository.save(newUser.get());
        }catch (UserUpdateException e){
            throw new UserUpdateException();
        }
    }
}
