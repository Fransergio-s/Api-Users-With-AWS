package compasso.com.br.apiuser.service.impl;

import compasso.com.br.apiuser.exceptions.*;
import compasso.com.br.apiuser.model.dto.UserRequestDto;
import compasso.com.br.apiuser.model.dto.UserResponseDto;
import compasso.com.br.apiuser.model.dto.UserUpdatePasswordDto;
import compasso.com.br.apiuser.model.dto.mapper.UserMapper;
import compasso.com.br.apiuser.model.entity.Address;
import compasso.com.br.apiuser.model.entity.User;
import compasso.com.br.apiuser.repository.UserRepository;
import compasso.com.br.apiuser.service.AddressService;
import compasso.com.br.apiuser.service.CepService;
import compasso.com.br.apiuser.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CepService cepService;
    private final AddressService addressService;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, CepService cepService, AddressServiceImpl addressService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.cepService = cepService;
        this.addressService = addressService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto create(UserRequestDto user) {
        try {
            Optional<User> test = userRepository.findByUsername(user.username());
            if (test.isPresent()) {
                throw new UserAlreadyExistException("User already exist");
            }
            Address address = addressService.create(
                    cepService.getAddressPerCep(user.zipCode()));

            User newUser = userMapper.toUser(user);
            newUser.setAddress(address);
            if (newUser.getPassword() == null) {
                throw new PasswordNullException("Null password is not valid");
            }
            newUser.setPassword(passwordEncoder.encode(user.password()));
            userRepository.save(newUser);
            return userMapper.toResponseDto(newUser,address);
        }catch (UserCreateException e) {
            throw new UserCreateException();
        }
    }

    @Override
    @Transactional
    public void updatePassword(UserUpdatePasswordDto user) {
        try {
            Optional<User> newUser = userRepository.findByUsername(user.username());
            if (newUser.isEmpty()) {
                throw new UserNotFoundException("User not found");
            }
            if (passwordEncoder.matches(newUser.get().getPassword(),user.oldPassword())){
                throw new UserPasswordNotMatch("Old password don't match");
            }
            if (user.newPassword() != null) {
                if(!user.newPassword().equals(user.oldPassword())) {
                    newUser.get().setPassword(passwordEncoder.encode(user.newPassword()));
                    userRepository.save(newUser.get());
                }
            }
        }catch (UserUpdateException e){
            throw new UserUpdateException();
        }
    }
}
