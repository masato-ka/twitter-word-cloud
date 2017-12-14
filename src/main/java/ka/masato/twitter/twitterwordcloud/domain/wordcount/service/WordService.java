package ka.masato.twitter.twitterwordcloud.domain.wordcount.service;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.Word;
import ka.masato.twitter.twitterwordcloud.domain.wordcount.repository.WordRepository;
import ka.masato.twitter.twitterwordcloud.exception.NotFoundDataException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Word getWord(String word){
        Word resultWord = wordRepository.findByWord(word);
        return resultWord;
    }

    public List<Word> getWorsdPeriod(LocalDateTime start, LocalDateTime end) {
        return null;//wordRepository.findByTimeBetween(start, end);
    }

    public List<Word> getWords(){
        return wordRepository.findAll();
    }

}
