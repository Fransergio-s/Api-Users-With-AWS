package compasso.com.br.apiuser.service;

import compasso.com.br.apiuser.model.dto.AddressRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface CepService {

    @GetMapping("{cep}/json")
    AddressRequestDto getAddressPerCep(@PathVariable("cep") String cep);
}
