package ka.masato.twitter.twitterwordcloud;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.service.WordCounterService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.TwitterException;

@Component
public class CrawlerTask {

    WordCounterService wordCounterService;

    public CrawlerTask(WordCounterService wordCounterService) {
        this.wordCounterService = wordCounterService;
    }

    @Scheduled(fixedDelay = 60000)
    public void doCrawlingTwitter() throws TwitterException {
        wordCounterService.indexingWordCount("ジャパンカップ",10);
    }

}
