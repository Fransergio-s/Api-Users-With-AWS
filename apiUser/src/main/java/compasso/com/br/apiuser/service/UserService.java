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
import org.springframework.stereotype.Service;

@Service

public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CepService cepService;
    private final AddressService  addressService;

    public UserService(UserRepository userRepository, UserMapper userMapper, CepService cepService, AddressService addressService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.cepService = cepService;
        this.addressService = addressService;
    }


    public UserResponseDto create(UserRequestDto user) {
        try {
            User test = userRepository.findByUsername(user.getUsername());
            if (test != null) {
                throw new UserAlreadyExistException("User already exist");
            }
            Address address = addressService.create(
                    cepService.getAddressPerCep(user.getZipCode()));

            User newUser = userMapper.toUser(user);
            newUser.setAddress(address);
            userRepository.save(newUser);
            return userMapper.toResponseDto(newUser);
        }catch (UserCreateException e){
            throw new UserCreateException();
        }
    }

    @Transactional
    public void updatePassword(UserUpdatePasswordDto user) {
        try {
            User newUser = userRepository.findByUsername(user.getUsername());
            if (newUser == null) {
                throw new UserNotFoundException("User not found");
            }
            if (!user.getOldPassword().equals(newUser.getPassword())) {
                throw new UserPasswordNotMatch("Old password don't match");
            }
            newUser.setPassword(user.getNewPassword());
            userRepository.save(newUser);
        }catch (UserUpdateException e){
            throw new UserUpdateException();
        }
    }
}
