package ba.edu.ibu.webengineeringproject.core.exceptions.auth;

import ba.edu.ibu.webengineeringproject.core.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidParametersException extends GeneralException {
    public InvalidParametersException() {
        super(HttpStatus.BAD_REQUEST.value());
    }

    public InvalidParametersException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }
}
