package compasso.com.br.apiuser.repository;


import compasso.com.br.apiuser.model.entity.UserJwtToken;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserJwtToken, Long> {
    Optional<UserJwtToken> findByToken(String jwt);
    void deleteAllTokensByUserId(Long userId);

    @Modifying
    @Query("UPDATE UserJwtToken u SET u.valid = false WHERE u.userId = :userId")
    void invalidateTokensByUserId(@Param("userId") Long userId);
}
