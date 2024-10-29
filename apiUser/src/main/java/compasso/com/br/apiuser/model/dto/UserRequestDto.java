package compasso.com.br.apiuser.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserRequestDto {

    private String username;
    private String password;
    private String email;
    private String zipCode;
}
