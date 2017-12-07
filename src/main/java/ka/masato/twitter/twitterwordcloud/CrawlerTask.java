package ka.masato.twitter.twitterwordcloud;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.service.WordCountsService;
import ka.masato.twitter.twitterwordcloud.infra.HerokuClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import twitter4j.TwitterException;

@Slf4j
@Component
public class CrawlerTask {

    private final WordCountsService wordCountsService;
    private final HerokuClient herokuClient;

    @Value("${crawling.repeat.interval:600000}")
    private int crawlingTime;

    public CrawlerTask(WordCountsService wordCountsService, HerokuClient herokuClient) {
        this.wordCountsService = wordCountsService;
        this.herokuClient = herokuClient;
    }

    @Scheduled(fixedDelayString = "${crawling.repeat.interval:600000}")
    private void doCrawlingTwitter() throws TwitterException {
        int timeSpan = crawlingTime/60000;
        wordCountsService.indexingWordCount("有馬記念",100, timeSpan);
    }

    @Scheduled(cron = "0 */20 * * * *", zone = "Asia/Tokyo")
    private void doKickSelf() {
        try {
            this.herokuClient.getHerokuOwn();
        }catch(HttpClientErrorException e){
            log.warn("The URL is not found.");
        }
    }

}
