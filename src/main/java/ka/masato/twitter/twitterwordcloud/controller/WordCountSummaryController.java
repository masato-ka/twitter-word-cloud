package ka.masato.twitter.twitterwordcloud.controller;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.WordCountSummary;
import ka.masato.twitter.twitterwordcloud.domain.wordcount.service.WordCountSummaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/summary/wordcounts")
public class WordCountSummaryController {

    private final WordCountSummaryService wordCountSummaryService;

    public WordCountSummaryController(WordCountSummaryService wordCountSummaryService) {
        this.wordCountSummaryService = wordCountSummaryService;
    }

    @GetMapping("/{word}")
    public WordCountSummary getWordCountSummary(@PathVariable String word){
        return wordCountSummaryService.getTotalWordCount(word);
    }

    @GetMapping()
    public List<WordCountSummary> getWordCountSummaryAll(){
        return wordCountSummaryService.getTotalWordCountAll();
    }
}
