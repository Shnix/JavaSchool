package exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class SecurityException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;

    public SecurityException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
