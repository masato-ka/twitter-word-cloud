package ka.masato.twitter.twitterwordcloud.domain.wordcount.service;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.WordCounts;
import ka.masato.twitter.twitterwordcloud.domain.wordcount.repository.WordCountsRepository;
import ka.masato.twitter.twitterwordcloud.infra.TwitterConnector;
import twitter4j.TwitterException;

import java.util.List;

public class WordCounterService {

    private final TwitterConnector twitterConnector;
    private final WordCountsRepository wordCountsRepository;

    public WordCounterService(TwitterConnector twitterConnector, WordCountsRepository wordCountsRepository) {
        this.twitterConnector = twitterConnector;
        this.wordCountsRepository = wordCountsRepository;
    }


    public Integer getWordCount(String word) {
        WordCounts result = wordCountsRepository.findByWord(word);
        return result.getCount();
    }

    public void indexingWordCount(String query, int limitSize) throws TwitterException {
        List<String> result = twitterConnector.getQueryResult();
    }

}
