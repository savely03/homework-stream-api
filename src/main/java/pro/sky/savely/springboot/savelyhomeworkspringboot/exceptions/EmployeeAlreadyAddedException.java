package pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions;


public class EmployeeAlreadyAddedException extends EmployeeException {
    public EmployeeAlreadyAddedException(String message) {
        super(message);
    }

}
