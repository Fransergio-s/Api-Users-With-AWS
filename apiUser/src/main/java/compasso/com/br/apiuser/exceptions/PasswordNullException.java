package compasso.com.br.apiuser.exceptions;

public class PasswordNullException extends RuntimeException {
    public PasswordNullException(String nullPasswordIsNotValid) {
        super(nullPasswordIsNotValid);
    }
}
