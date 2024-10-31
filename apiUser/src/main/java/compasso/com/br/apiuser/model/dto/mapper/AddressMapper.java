package compasso.com.br.apiuser.model.dto.mapper;

import compasso.com.br.apiuser.model.dto.AddressRequestDto;
import compasso.com.br.apiuser.model.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toAddress(AddressRequestDto addressRequestDto) {
        return new Address(
                addressRequestDto.zipCode(), addressRequestDto.street(),
                addressRequestDto.complement(), addressRequestDto.neighborhood(),
                addressRequestDto.city(), addressRequestDto.state());
    }
}
