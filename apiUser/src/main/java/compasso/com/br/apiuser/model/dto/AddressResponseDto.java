package compasso.com.br.apiuser.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressResponseDto(
        String zipCode,
        String street,
        String complement,
        String neighborhood,
        String city,
        String state) {
}
