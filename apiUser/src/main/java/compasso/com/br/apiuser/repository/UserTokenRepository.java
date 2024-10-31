package compasso.com.br.apiuser.repository;


import compasso.com.br.apiuser.model.entity.UserJwtToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserJwtToken, Long> {
    Optional<UserJwtToken> findByToken(String jwt);
    void deleteAllTokensByUserId(Long userId);
}
