package pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class EmployeeStoragelsFullException extends RuntimeException {

    public EmployeeStoragelsFullException(String message) {
        super(message);
    }
}
