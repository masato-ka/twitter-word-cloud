package ka.masato.twitter.twitterwordcloud.domain.wordcount.service;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
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
public class WordCounterService {

    private final TwitterConnector twitterConnector;
    private final WordCountsRepository wordCountsRepository;
    private final Tokenizer tokenizer;
    
    public WordCounterService(TwitterConnector twitterConnector, WordCountsRepository wordCountsRepository) throws IOException {
        this.twitterConnector = twitterConnector;
        this.wordCountsRepository = wordCountsRepository;
        String fileName = WordCounterService.class.getClassLoader().getResource("userDic.csv").getPath();
        tokenizer = new Tokenizer.Builder().userDictionary(fileName).build();
    }


    public Integer getWordCount(String word) {
        WordCounts result = wordCountsRepository.findByWord(word);
        return result.getCount();
    }

    public void indexingWordCount(String query, int limitSize) throws TwitterException {

        String periodOfQuery = getPeriodOfQuery(1);
        log.info(periodOfQuery);
        List<String> result = getTweetsWithQuery(query, limitSize, periodOfQuery);
        Map<Object, Long> word = countOfWord(result);
        log.info(word.toString());

    }

    private List<String> getTweetsWithQuery(String query, int limitSize, String periodOfQuery) throws TwitterException {
        twitterConnector.setQuery(query+" "+periodOfQuery,limitSize);
        return twitterConnector.getQueryResult();
    }

    private Map<Object, Long> countOfWord(List<String> result) {
        return result.stream()
                    .map(line -> {
                        List<Token> tokens = tokenizer.tokenize(line);
                        List<String> filterd = tokens
                                .stream()
                                .filter(e -> e.getPartOfSpeechLevel1().equals("馬名"))
                                .map(e -> e.getSurface()).distinct()
                                .collect(Collectors.toList());
                        return filterd;
                    })
                    .filter(e -> e.size() > 0)
                    .flatMap(e -> e.stream())
                    .collect(Collectors.groupingBy(element->element, Collectors.counting()));
    }

    private String getPeriodOfQuery(int minutesTimeSpan) {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.now();
        sb.append("since:");
        sb.append(startTime.minusMinutes(minutesTimeSpan).format(formatter));
        sb.append("_JST ");
        sb.append("until:");
        sb.append(startTime.format(formatter));
        sb.append("_JST");
        return sb.toString();
    }


}
