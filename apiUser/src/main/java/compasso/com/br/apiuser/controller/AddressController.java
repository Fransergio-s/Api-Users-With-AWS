package compasso.com.br.apiuser.controller;

import compasso.com.br.apiuser.model.dto.AddressRequestDto;
import compasso.com.br.apiuser.service.CepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Address", description = "Search for an address based on a zip code")
@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final CepService cepService;

    public AddressController(CepService cepService) {
        this.cepService = cepService;
    }

    @Operation(summary = "It has all searches for an address based on an entered zip code",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok->Address Found.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AddressRequestDto.class))),
                    @ApiResponse(responseCode = "404", description = "Not Found: Address not found with this param.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error: Unexpected error occurred.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            })
    @GetMapping("/{cep}")
    public AddressRequestDto findAddress(@PathVariable String cep) {
        return cepService.getAddressPerCep(cep);
    }

}
