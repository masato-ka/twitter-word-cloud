package ka.masato.twitter.twitterwordcloud.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExceptionHandling {

    @AfterThrowing("within(ka.masato.twitter.twitterwordcloud.infra.TwitterConnector)")
    public void handlingTwitterException() {
        log.error("Exception. ");
    }

}
