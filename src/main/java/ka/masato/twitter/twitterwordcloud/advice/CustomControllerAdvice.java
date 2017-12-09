package ka.masato.twitter.twitterwordcloud.advice;

import ka.masato.twitter.twitterwordcloud.exception.NotFoundDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String NotFoundExceptionHandler(Exception e){
        log.warn("Request non exsists url from clinet.");
        return "Not Found.";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String HttpRequestMethodNotSupportedHandler(Exception e){
        log.warn("Request non exsists url from clinet.");
        return "Method not allowed.";
    }

    @ExceptionHandler(NotFoundDataException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String NotFoundDataExceptionHandller(Exception e) {
        log.warn("The request data not found.");
        return "The request data not found.";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String HttpRequestIllegalArgumentException(Exception e) {
        log.warn("Request is Bad request.");
        e.printStackTrace();
        return "BAD REQUEST";
    }
}
