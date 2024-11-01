package compasso.com.br.apiuser.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


public record UserRequestDto(
        String username,
        String password,
        String email,
        String zipCode) {
}
