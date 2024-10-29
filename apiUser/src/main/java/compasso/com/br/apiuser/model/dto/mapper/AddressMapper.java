package compasso.com.br.apiuser.model.dto.mapper;

import compasso.com.br.apiuser.model.dto.AddressDto;
import compasso.com.br.apiuser.model.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toAddress(AddressDto addressDto) {
        return new Address(
                addressDto.getZipCode(),addressDto.getStreet(),
                addressDto.getComplement(),addressDto.getNeighborhood(),
                addressDto.getCity(),addressDto.getState());
    }
}
