package ka.masato.twitter.twitterwordcloud.domain.wordcount.service;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.WordCountSummary;
import ka.masato.twitter.twitterwordcloud.domain.wordcount.repository.WordCountSummaryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WordCountSummaryService {

    private final WordCountSummaryRepository wordCountSummaryRepository;

    public WordCountSummaryService(WordCountSummaryRepository wordCountSummaryRepository) {
        this.wordCountSummaryRepository = wordCountSummaryRepository;
    }

    public WordCountSummary getTotalWordCountPeriod(String word, LocalDateTime start, LocalDateTime end) {
        return wordCountSummaryRepository.sumCountByWordAndPeriod(word, start, end);
    }

    public List<WordCountSummary> getTotalWordCountPeriod(LocalDateTime start, LocalDateTime end) {
        return wordCountSummaryRepository.sumCountByPeriod(start, end);
    }

    public WordCountSummary getTotalWordCount(String word){
        return wordCountSummaryRepository.sumCountByWord(word);
    }

    public List<WordCountSummary> getTotalWordCountAll(){
        return wordCountSummaryRepository.sumCountAll();
    }


}
