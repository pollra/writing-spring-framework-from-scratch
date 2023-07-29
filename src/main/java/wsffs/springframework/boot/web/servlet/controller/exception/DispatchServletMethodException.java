package wsffs.springframework.boot.web.servlet.controller.exception;

public class DispatchServletMethodException extends RuntimeException {
  public DispatchServletMethodException(String message) {
    super(message);
  }

  public DispatchServletMethodException(Throwable cause) {
    super(cause);
  }
}
