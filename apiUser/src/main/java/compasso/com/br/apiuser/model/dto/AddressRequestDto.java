package compasso.com.br.apiuser.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressRequestDto {
    @JsonProperty("cep")
    private String zipCode;
    @JsonProperty("logradouro")
    private String street;
    @JsonProperty("complemento")
    private String complement;
    @JsonProperty("bairro")
    private String neighborhood;
    @JsonProperty("localidade")
    private String city;
    @JsonProperty("estado")
    private String state;
}
