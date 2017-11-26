package ka.masato.twitter.twitterwordcloud.api;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.WordCounts;
import ka.masato.twitter.twitterwordcloud.domain.wordcount.service.WordCountsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/wordcounts")
public class WordCountControll {

    private final WordCountsService wordCountsService;


    public WordCountControll(WordCountsService wordCountsService) {
        this.wordCountsService = wordCountsService;
    }

    @GetMapping("/{word}")
    public List<WordCounts> getWordCounts(@PathVariable String word){
        return wordCountsService.getWordCounts(word);
    }
}

