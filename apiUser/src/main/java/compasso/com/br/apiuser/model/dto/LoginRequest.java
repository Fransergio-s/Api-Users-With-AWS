package compasso.com.br.apiuser.model.dto;


public record LoginRequest(
        String username,
        String password
) {
}
