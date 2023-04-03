package pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions;



public class EmployeeNotFoundException extends EmployeeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
