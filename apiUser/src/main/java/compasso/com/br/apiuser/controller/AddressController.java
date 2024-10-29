package compasso.com.br.apiuser.controller;

import compasso.com.br.apiuser.model.entity.Address;
import compasso.com.br.apiuser.service.CepService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final CepService cepService;

    public AddressController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public Address findAddress(@PathVariable String cep) {
        return cepService.getAddressPerCep(cep);
    }

}
