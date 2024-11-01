package compasso.com.br.apiuser.model.dto;

import compasso.com.br.apiuser.model.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public record UserResponseDto(
        String username,
        String email,
        AddressResponseDto address
) {
}
