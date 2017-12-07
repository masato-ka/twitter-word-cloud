package ka.masato.twitter.twitterwordcloud.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String NotFoundExceptionHandler(Exception e){
        log.warn("Request non exsists url from clinet.");
        return "Not Found.";
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String HttpRequestMethodNotSupportedHandler(Exception e){
        log.warn("Request non exsists url from clinet.");
        return "Method not allowed.";
    }

}
