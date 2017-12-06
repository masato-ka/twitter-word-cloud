package ka.masato.twitter.twitterwordcloud;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.service.WordCountsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.TwitterException;

@Component
public class CrawlerTask {

    private final WordCountsService wordCountsService;

    @Value("${crawling.repeat.interval:600000}")
    private int crawlingTime;

    public CrawlerTask(WordCountsService wordCountsService) {
        this.wordCountsService = wordCountsService;
    }

    @Scheduled(fixedDelayString = "${crawling.repeat.interval:600000}")
    private void doCrawlingTwitter() throws TwitterException {
        int timeSpan = crawlingTime/60000;
        wordCountsService.indexingWordCount("有馬記念",100, timeSpan);
    }

}
