package ka.masato.twitter.twitterwordcloud.domain.wordcount.service.advice;

import javassist.NotFoundException;
import ka.masato.twitter.twitterwordcloud.exception.ErrorQueryTimeException;
import ka.masato.twitter.twitterwordcloud.exception.ErrorTimeParameterIndicateFutureTime;
import ka.masato.twitter.twitterwordcloud.exception.NotFoundDataException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Aspect
public class ExceptionServiceAdvice {

    @AfterReturning(value = "execution(* *..*Service.get*(..))", returning = "ret")
    public void returningNullCheck(JoinPoint jp, Object ret) {
        log.info("Result chekck after do function." + jp.getSignature());
        if (ret == null) {
            throw new NotFoundDataException();
        }

        if (ret.getClass() == ArrayList.class) {
            ArrayList result = (ArrayList) ret;
            if (result.size() == 0) {
                log.warn("jp.getSignature()" + ": result is not found.");
                throw new NotFoundDataException();
            }
        }
    }

    //TODO もうちょっと綺麗に書く。
    @Before(value = "execution(* *..*Service.*Period(..))")
    public void mandatoryCheckTimeArgument(JoinPoint jp) {
        LocalDateTime[] parameters = new LocalDateTime[2];
        Object[] args = jp.getArgs();
        int i = 0;
        extractLocalDateTimeargs(parameters, args, i);
        if (parameters[0] == null || parameters[1] == null) {
            return;
        }

        if (parameters[0].isAfter(parameters[1])) {
            log.error("Time parameters relation is illegal. ");
            throw new ErrorQueryTimeException();
        }

        LocalDateTime nowTime = LocalDateTime.now();
        if (nowTime.isBefore(parameters[0])) {
            throw new ErrorTimeParameterIndicateFutureTime();
        }

    }

    private void extractLocalDateTimeargs(LocalDateTime[] parameters, Object[] args, int i) {
        for (Object arg : args) {
            if (arg.getClass() == LocalDateTime.class) {
                parameters[i++] = (LocalDateTime) arg;
            }
        }
    }

}
