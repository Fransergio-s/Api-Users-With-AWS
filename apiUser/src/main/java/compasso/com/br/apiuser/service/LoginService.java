package compasso.com.br.apiuser.service;


import compasso.com.br.apiuser.exceptions.UserLoginException;
import compasso.com.br.apiuser.jwt.JwtService;
import compasso.com.br.apiuser.model.dto.LoginRequest;
import compasso.com.br.apiuser.model.dto.LoginResponse;
import compasso.com.br.apiuser.model.dto.LogoutRequest;
import compasso.com.br.apiuser.model.entity.CustomUserDetails;
import compasso.com.br.apiuser.model.entity.User;
import compasso.com.br.apiuser.model.entity.UserJwtToken;
import compasso.com.br.apiuser.repository.UserRepository;
import compasso.com.br.apiuser.repository.UserTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {
    @Value("${security.jwt.token.expire-length}")
    private Long expirationTime;

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserTokenRepository tokenRepository;

    @Transactional
    public LoginResponse login(LoginRequest request) {
        try {
            User user = userRepository
                    .findByUsername(request.username())
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
            tokenRepository.deleteAllTokensByUserId(user.getId());

            if (!user.getPassword().equals(request.password())) {
                throw new BadCredentialsException("Bad credentials");
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken(request.username(), request.password());
            authenticationManager.authenticate(authentication);

            UserDetails userDetails = new CustomUserDetails(user);
            String token = jwtService.createTokenWithoutClaims(userDetails);

            UserJwtToken userToken = new UserJwtToken();
            userToken.setUserId(user.getId());
            userToken.setToken(token);
            userToken.setCreatedAt(LocalDateTime.now());
            userToken.setExpiresAt(LocalDateTime.now().plusMinutes(expirationTime));

            tokenRepository.save(userToken);
            return new LoginResponse(user.getUsername(), token);
        }catch (UserLoginException error){
            throw new UserLoginException("Error: " + error);
        }
    }

//
//    @Transactional
//    public void logout(LogoutRequest request, HttpServletRequest header) {
//        User user = userRepository
//                .findByUsername(request.username())
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
//        tokenRepository.invalidateTokensByUserId(user.getId());
//    }

}