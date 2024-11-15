package ee.gert.veebipood.exception;

import org.hibernate.TransientPropertyValueException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.server.ExportException;
import java.util.Date;

//ExceptionHandler
@ControllerAdvice
public class ExceptionCatcher {
    @ExceptionHandler
    public ResponseEntity<ErrorMessage> CustomException(ValidationException e){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setName(e.getMessage());
        errorMessage.setDate(new Date());
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> CustomException(TransientPropertyValueException e){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setName("kategooria lisamatta!"); //FIXME: IllegalStateException or TransientPropertyValueException can be about something else as well
        errorMessage.setDate(new Date());
        return ResponseEntity.badRequest().body(errorMessage);
    }


// Lopus:
//    @ExceptionHandler
//    public ResponseEntity<ErrorMessage> defaultException(Exception e){
//        ErrorMessage errorMessage = new ErrorMessage();
//        errorMessage.setName("Unexpected error");
//        errorMessage.setDate(new Date());
//        return ResponseEntity.badRequest().body(errorMessage);
//    }


}
