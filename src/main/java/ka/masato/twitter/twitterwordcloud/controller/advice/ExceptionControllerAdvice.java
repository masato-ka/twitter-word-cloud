package ka.masato.twitter.twitterwordcloud.controller.advice;

import ka.masato.twitter.twitterwordcloud.controller.error.ErrorResponse;
import ka.masato.twitter.twitterwordcloud.exception.ErrorQueryTimeException;
import ka.masato.twitter.twitterwordcloud.exception.ErrorTimeParameterIndicateFutureTime;
import ka.masato.twitter.twitterwordcloud.exception.NotFoundDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
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
        log.error("Request non exsists url from clinet.");
        ErrorResponse errorResponse = new ErrorResponse(404, e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(NotFoundDataException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse NotFoundDataExceptionHandller(Exception e) {
        log.error("The request data not found.");
        ErrorResponse errorResponse = new ErrorResponse(404, e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse HttpRequestIllegalArgumentException(Exception e) {
        log.error("Request is Bad request.");
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

    @ExceptionHandler(ErrorQueryTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse ErrorQueryTimeExceptionHandler(Exception e) {
        log.error("Bad request, because specific time is error");
        ErrorResponse errorResponse = new ErrorResponse(400, "Time1 and Time2 are exchange.");
        return errorResponse;
    }

    @ExceptionHandler(ErrorTimeParameterIndicateFutureTime.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse ErrorTimeParameterIndicateFutureTimeHandler(Exception e) {
        log.error("In start time parameter is future time date.");
        ErrorResponse errorResponse = new ErrorResponse(400,
                "In start time Parameter is future time date.");
        return errorResponse;
    }

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse ConversionFailedExceptionHandler(Exception e) {
        log.error("Fail conversion from request to Object");
        log.error(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(400,
                "Start and End parameter format is yyyy-MM-dd-HH:mm:ss.SSS .");
        return errorResponse;
    }

}
