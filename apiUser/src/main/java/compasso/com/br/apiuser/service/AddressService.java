package compasso.com.br.apiuser.service;

import compasso.com.br.apiuser.model.dto.AddressRequestDto;
import compasso.com.br.apiuser.model.entity.Address;

public interface AddressService {

    Address create(AddressRequestDto addressRequestDto);
}
