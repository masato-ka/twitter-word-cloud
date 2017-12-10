package ka.masato.twitter.twitterwordcloud.domain.wordcount.service.advice;

import ka.masato.twitter.twitterwordcloud.exception.NotFoundDataException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

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

}
