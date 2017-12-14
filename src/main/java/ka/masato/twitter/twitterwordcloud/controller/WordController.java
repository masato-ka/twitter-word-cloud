package ka.masato.twitter.twitterwordcloud.controller;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.Word;
import ka.masato.twitter.twitterwordcloud.domain.wordcount.service.WordService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/word")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping
    public List<Word> getWords(@RequestParam(value = "start", required = false)
                               @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss.SSS")
                                       LocalDateTime start,
                               @RequestParam(value = "end", required = false)
                               @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss.SSS")
                                       LocalDateTime end) {
        if (start == null || end == null) {
            return wordService.getWords();
        }
        return wordService.getWorsdPeriod(start, end);
    }

    @GetMapping("/{word}")
    public Word getWordsOne(@PathVariable("word") String word) {
        return wordService.getWord(word);
    }
}
