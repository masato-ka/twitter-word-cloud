package ka.masato.twitter.twitterwordcloud.controller.advice;

import ka.masato.twitter.twitterwordcloud.controller.error.ErrorResponse;
import ka.masato.twitter.twitterwordcloud.exception.NotFoundDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse NotFoundExceptionHandler(Exception e) {
        log.warn("Request non exsists url from clinet.");
        ErrorResponse errorResponse = new ErrorResponse(404, e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse HttpRequestMethodNotSupportedHandler(Exception e) {
        log.warn("Request non exsists url from clinet.");
        ErrorResponse errorResponse = new ErrorResponse(404, e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(NotFoundDataException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse NotFoundDataExceptionHandller(Exception e) {
        log.warn("The request data not found.");
        ErrorResponse errorResponse = new ErrorResponse(404, e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse HttpRequestIllegalArgumentException(Exception e) {
        log.warn("Request is Bad request.");
        ErrorResponse errorResponse = new ErrorResponse(400, e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse MissingServletRequestParameterExceptionHandler(Exception e) {
        log.error("Missing request parameter." + e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(400, e.getMessage());
        // e.printStackTrace();
        return errorResponse;
    }

}
