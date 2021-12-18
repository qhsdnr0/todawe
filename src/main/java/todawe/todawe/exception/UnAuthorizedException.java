package todawe.todawe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException() {}

    public UnAuthorizedException(Throwable cause) {
        super(cause);
    }
}
