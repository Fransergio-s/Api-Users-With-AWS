package compasso.com.br.apiuser.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String userAlreadyExist) {
        super(userAlreadyExist);
    }
}
