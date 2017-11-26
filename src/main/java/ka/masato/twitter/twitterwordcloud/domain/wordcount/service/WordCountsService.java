package ka.masato.twitter.twitterwordcloud.domain.wordcount.service;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import ka.masato.twitter.twitterwordcloud.domain.tweet.domain.Tweet;
import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.WordCounts;
import ka.masato.twitter.twitterwordcloud.domain.wordcount.repository.WordCountsRepository;
import ka.masato.twitter.twitterwordcloud.infra.TwitterConnector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WordCountsService {

    private final TwitterConnector twitterConnector;
    private final WordCountsRepository wordCountsRepository;
    private final Tokenizer tokenizer;
    
    public WordCountsService(TwitterConnector twitterConnector, WordCountsRepository wordCountsRepository) throws IOException {
        this.twitterConnector = twitterConnector;
        this.wordCountsRepository = wordCountsRepository;
        String fileName = WordCountsService.class.getClassLoader().getResource("userDic.csv").getPath();
        tokenizer = new Tokenizer.Builder().userDictionary(fileName).build();
    }


    public List<WordCounts> getWordCounts(String word) {
        List<WordCounts> result = wordCountsRepository.findByWord(word);
        return result;
    }

    public void indexingWordCount(String query, int limitSize, int timeSpan) throws TwitterException {

        LocalDateTime localDateTime = LocalDateTime.now();
        String periodOfQuery = getPeriodOfQuery(timeSpan, localDateTime);
        log.info(periodOfQuery);
        List<Tweet> result = getTweetsWithQuery(query, limitSize, periodOfQuery);
        Map<String, Long> words = countOfWord(result);
        log.info(words.toString());

        for (String key : words.keySet()) {
            WordCounts wordCounts = new WordCounts();
            wordCounts.setWord(key);
            wordCounts.setCount(words.get(key));
            wordCounts.setTime(localDateTime);
            wordCountsRepository.save(wordCounts);
        }

    }

    private List<Tweet> getTweetsWithQuery(String query, int limitSize, String periodOfQuery) throws TwitterException {
        twitterConnector.setQuery(query+" "+periodOfQuery,limitSize);
        return twitterConnector.getQueryResult();
    }

    private Map<String, Long> countOfWord(List<Tweet> result) {
        return result.stream()
                    .map(tweet -> {
                        List<Token> tokens = tokenizer.tokenize(tweet.getText());
                        List<String> filtered = tokens
                                .stream()
                                .filter(e -> e.getPartOfSpeechLevel1().equals("馬名"))
                                .map(e -> e.getSurface()).distinct()
                                .collect(Collectors.toList());
                        return filtered;
                    })
                    .filter(e -> e.size() > 0)
                    .flatMap(e -> e.stream())
                    .collect(Collectors.groupingBy(element->element, Collectors.counting()));
    }

    private String getPeriodOfQuery(int minutesTimeSpan, LocalDateTime startTime) {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
        sb.append("since:");
        sb.append(startTime.minusMinutes(minutesTimeSpan).format(formatter));
        sb.append("_JST ");
        sb.append("until:");
        sb.append(startTime.format(formatter));
        sb.append("_JST");
        return sb.toString();
    }


}
