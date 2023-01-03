package xlmuploader.exception;

import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DeviceInfoControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
      WebRequest request) {

    String errorsString = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
        .collect(Collectors.joining(", "));

    return handleExceptionInternal(ex, new ExceptionMessage(errorsString), new HttpHeaders(),
        HttpStatus.UNPROCESSABLE_ENTITY, request);
  }

  @ExceptionHandler({CantProcessDeviceInfoXmlException.class})
  public ResponseEntity<Object> handleConstraintViolationException(RuntimeException ex,
      WebRequest request) {

    return handleExceptionInternal(ex, new ExceptionMessage(ex.getMessage()), new HttpHeaders(),
        HttpStatus.UNPROCESSABLE_ENTITY, request);
  }
}
