package compasso.com.br.apiuser.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressRequestDto(
        @JsonProperty("cep")
        String zipCode,
        @JsonProperty("logradouro")
        String street,
        @JsonProperty("complemento")
        String complement,
        @JsonProperty("bairro")
        String neighborhood,
        @JsonProperty("localidade")
        String city,
        @JsonProperty("estado")
        String state) {

}
