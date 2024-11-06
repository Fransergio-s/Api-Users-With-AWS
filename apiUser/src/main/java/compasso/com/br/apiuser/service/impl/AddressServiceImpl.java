package compasso.com.br.apiuser.service.impl;

import compasso.com.br.apiuser.exceptions.CreateAddressException;
import compasso.com.br.apiuser.model.dto.AddressRequestDto;
import compasso.com.br.apiuser.model.dto.mapper.AddressMapper;
import compasso.com.br.apiuser.model.entity.Address;
import compasso.com.br.apiuser.repository.AddressRepository;
import compasso.com.br.apiuser.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressMapper addressMapper, AddressRepository addressRepository) {
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
    }

    @Override
    public Address create(AddressRequestDto addressRequestDto) {
        try {
            return addressRepository.save(addressMapper.toAddress(addressRequestDto));
        }catch (CreateAddressException e){
            throw new CreateAddressException();
        }
    }
}
