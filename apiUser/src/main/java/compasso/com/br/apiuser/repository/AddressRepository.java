package compasso.com.br.apiuser.repository;

import compasso.com.br.apiuser.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
