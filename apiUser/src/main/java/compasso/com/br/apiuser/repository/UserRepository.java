package compasso.com.br.apiuser.repository;

import compasso.com.br.apiuser.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
