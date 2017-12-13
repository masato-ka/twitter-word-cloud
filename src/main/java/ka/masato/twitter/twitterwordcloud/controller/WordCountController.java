package ka.masato.twitter.twitterwordcloud.controller;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.WordCounts;
import ka.masato.twitter.twitterwordcloud.domain.wordcount.service.WordCountsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path="/api/v1/wordcounts")
public class WordCountController {

    private final WordCountsService wordCountsService;


    public WordCountController(WordCountsService wordCountsService) {
        this.wordCountsService = wordCountsService;
    }

    @GetMapping("/{word}")
    public List<WordCounts> getWordCounts(@PathVariable("word") String word) {
        return wordCountsService.getWordCounts(word);
    }

    @GetMapping()
    public List<WordCounts> getWordCountsWithTime(@DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss.SSS") @RequestParam LocalDateTime time1,
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss.SSS") @RequestParam LocalDateTime time2){

        return wordCountsService.getWordCountsPeriod(time1, time2);
    }

}

