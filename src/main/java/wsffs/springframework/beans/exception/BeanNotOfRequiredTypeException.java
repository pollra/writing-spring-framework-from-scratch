package wsffs.springframework.beans.exception;

public class BeanNotOfRequiredTypeException extends RuntimeException {
    public BeanNotOfRequiredTypeException(String message) {
        super(message);
    }
}
