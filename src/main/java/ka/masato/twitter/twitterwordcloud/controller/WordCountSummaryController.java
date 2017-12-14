package ka.masato.twitter.twitterwordcloud.controller;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.WordCountSummary;
import ka.masato.twitter.twitterwordcloud.domain.wordcount.service.WordCountSummaryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/summary/wordcounts")
public class WordCountSummaryController {

    private final WordCountSummaryService wordCountSummaryService;

    public WordCountSummaryController(WordCountSummaryService wordCountSummaryService) {
        this.wordCountSummaryService = wordCountSummaryService;
    }

    @GetMapping("/{word}")
    public WordCountSummary getWordCountSummary(@PathVariable("word") String word,
                                                @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss.SSS")
                                                @RequestParam(value = "start", required = false) LocalDateTime start,
                                                @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss.SSS")
                                                @RequestParam(value = "end", required = false) LocalDateTime end) {
        if (start == null || end == null) {
            return wordCountSummaryService.getTotalWordCount(word);
        }
        return wordCountSummaryService.getTotalWordCountPeriod(word, start, end);
    }

    //TODO Doja timeで落ちた時のBad requestをハンドルできてない。全体的にできていないのでは？
    @GetMapping()
    public List<WordCountSummary> getWordCountSummaryAll(@DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss.SSS")
                                                         @RequestParam(value = "start", required = false) LocalDateTime start,
                                                         @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss.SSS")
                                                         @RequestParam(value = "end", required = false) LocalDateTime end) {
        if (start == null || end == null) {
            return wordCountSummaryService.getTotalWordCountAll();
        }
        return wordCountSummaryService.getTotalWordCountPeriod(start, end);
    }


}
