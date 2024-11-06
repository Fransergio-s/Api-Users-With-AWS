package compasso.com.br.apiuser.service;

import compasso.com.br.apiuser.model.dto.LoginRequest;
import compasso.com.br.apiuser.model.dto.LoginResponse;

public interface LoginService {

    LoginResponse login(LoginRequest request);
}
