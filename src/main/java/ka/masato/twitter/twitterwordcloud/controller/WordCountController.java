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
@RequestMapping(path = "/api/v1/words/")
public class WordCountController {

    private final WordCountsService wordCountsService;
    //時間クエリの投げかけがJST とUTC混在問題。

    public WordCountController(WordCountsService wordCountsService) {
        this.wordCountsService = wordCountsService;
    }

    @GetMapping("/{word}/count")
    public List<WordCounts> getWordCounts(@PathVariable("word") String word) {
        return wordCountsService.getWordCounts(word);
    }

    @GetMapping("/count")
    public List<WordCounts> getWordCountsWithTime(@DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss.SSS")
                                                  @RequestParam LocalDateTime start,
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss.SSS")
                                                  @RequestParam LocalDateTime end) {
        return wordCountsService.getWordCountsPeriod(start, end);
    }

}

