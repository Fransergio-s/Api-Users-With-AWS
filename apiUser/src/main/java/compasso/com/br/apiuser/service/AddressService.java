package compasso.com.br.apiuser.service;

import compasso.com.br.apiuser.model.dto.AddressDto;
import compasso.com.br.apiuser.model.dto.UserRequestDto;
import compasso.com.br.apiuser.model.dto.UserResponseDto;
import compasso.com.br.apiuser.model.dto.mapper.AddressMapper;
import compasso.com.br.apiuser.model.entity.Address;
import compasso.com.br.apiuser.model.entity.User;
import compasso.com.br.apiuser.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    public AddressService(AddressMapper addressMapper, AddressRepository addressRepository) {
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
    }

    public Address create(AddressDto addressDto) {
        return addressRepository.save(addressMapper.toAddress(addressDto));
    }
}
