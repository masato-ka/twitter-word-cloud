package ka.masato.twitter.twitterwordcloud.domain.wordcount.service;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.Word;
import ka.masato.twitter.twitterwordcloud.domain.wordcount.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Word getWord(String word){
        return wordRepository.findByWord(word);
    }

    public List<Word> getWords(){
        return wordRepository.findAll();
    }

}
