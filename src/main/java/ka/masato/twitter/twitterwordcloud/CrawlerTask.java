package ka.masato.twitter.twitterwordcloud;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.service.WordCounterService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CrawlerTask {

    WordCounterService wordCounterService;

    @Scheduled
    public void doCwalingTwitter(){

    }

}
