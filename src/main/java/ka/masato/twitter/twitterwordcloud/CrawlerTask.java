package ka.masato.twitter.twitterwordcloud;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.service.WordCountsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.TwitterException;

@Component
public class CrawlerTask {

    private final WordCountsService wordCountsService;

    public CrawlerTask(WordCountsService wordCountsService) {
        this.wordCountsService = wordCountsService;
    }

    @Scheduled(fixedDelay = 60000)
    public void doCrawlingTwitter() throws TwitterException {
        wordCountsService.indexingWordCount("ジャパンカップ",10);
    }

}
