package max.lab.springboot.latest.bookservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class MyControllerAdvicer {
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, String>> requestHandlingNoHandlerFound(HttpServletRequest req, NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("test", "resource not found"));
    }
}
