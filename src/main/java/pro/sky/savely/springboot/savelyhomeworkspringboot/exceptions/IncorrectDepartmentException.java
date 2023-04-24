package pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectDepartmentException extends RuntimeException {

    public IncorrectDepartmentException(String message) {
        super(message);
    }
}
