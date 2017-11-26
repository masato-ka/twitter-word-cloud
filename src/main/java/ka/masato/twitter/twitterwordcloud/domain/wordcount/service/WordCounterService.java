package ka.masato.twitter.twitterwordcloud.domain.wordcount.service;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.WordCounts;
import ka.masato.twitter.twitterwordcloud.domain.wordcount.repository.WordCountsRepository;
import ka.masato.twitter.twitterwordcloud.infra.TwitterConnector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

import java.io.IOException;
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
        twitterConnector.setQuery(query,limitSize);
        List<String> result = twitterConnector.getQueryResult();

        Map<Object, Long> word = result.stream()
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

        log.info(word.toString());

    }



}
