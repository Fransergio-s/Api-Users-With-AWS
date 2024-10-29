package compasso.com.br.apiuser.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserUpdatePasswordDto {

    private String username;
    private String oldPassword;
    private String newPassword;
}
