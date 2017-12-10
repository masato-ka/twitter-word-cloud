package ka.masato.twitter.twitterwordcloud.infrastructure.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExceptionInfraAdvice {

    @AfterThrowing("within(ka.masato.twitter.twitterwordcloud.infrastructure.TwitterConnector)")
    public void handlingTwitterException() {
        log.error("Failed connect to Twitter API. please check the setting. ");
    }

}
