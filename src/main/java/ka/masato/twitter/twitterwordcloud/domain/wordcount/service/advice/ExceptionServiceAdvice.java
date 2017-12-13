package ka.masato.twitter.twitterwordcloud.domain.wordcount.service.advice;

import ka.masato.twitter.twitterwordcloud.exception.ErrorQueryTimeException;
import ka.masato.twitter.twitterwordcloud.exception.NotFoundDataException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Component
@Aspect
public class ExceptionServiceAdvice {

    @AfterReturning(value = "execution(* *..*Service.get*(..))", returning = "ret")
    public void logging(JoinPoint jp, Object ret) {
        log.info("After do function." + jp.getSignature());
        if (ret == null) {
            throw new NotFoundDataException();
        }

    }

    //TODO 検索期間処理をAdviceで実装する。
    @Before(value = "execution(* *..*Service.*Period(..))")
    public void mandatoryCheckTimeArgument(JoinPoint jp) {
        LocalDateTime[] parameters = new LocalDateTime[2];
        Object[] args = jp.getArgs();
        int i = 0;
        for (Object arg : args) {
            if (arg.getClass() == LocalDateTime.class) {
                parameters[i++] = (LocalDateTime) arg;
            }
        }
        if (parameters[0] == null || parameters[1] == null) {
            return;
        }

        if (parameters[0].isAfter(parameters[1])) {
            log.error("Time parameters relation is illegal. ");
            throw new ErrorQueryTimeException();
        }
    }

}
