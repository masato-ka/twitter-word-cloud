package ka.masato.twitter.twitterwordcloud.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TwitterConnector {

    private final Twitter twitter;
    private final Query query;

    public TwitterConnector() {
        this.twitter = new TwitterFactory().getInstance();
        query = new Query();
    }

    public void setQuery(String queryStr, int resultCount){
        query.setQuery(queryStr);
        query.setCount(resultCount);
    }

    public List<String> getQueryResult() throws TwitterException {
        QueryResult queryResult = twitter.search(query);
        log.debug("Get twitter query size is " + queryResult.getCount());
        return queryResult.getTweets().stream()
                    .map(tweet -> tweet.getText())
                    .collect(Collectors.toList());
    }

}
