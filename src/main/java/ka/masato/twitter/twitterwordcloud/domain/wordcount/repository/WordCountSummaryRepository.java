package ka.masato.twitter.twitterwordcloud.domain.wordcount.repository;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.WordCountSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WordCountSummaryRepository extends JpaRepository<WordCountSummary,Integer>{

    @Query(value = "SELECT NEW ka.masato.twitter.twitterwordcloud.domain.wordcount.model.WordCountSummary(w.word.wordId,w.word,SUM(w.count))" +
            "FROM WordCounts w WHERE w.word.word = :word AND w.time BETWEEN :startTime AND :endTime GROUP BY w.word")
    WordCountSummary sumCountByWordAndPeriod(@Param("word") String word, @Param("startTime") LocalDateTime start, @Param("endTime") LocalDateTime end);

    @Query(value = "SELECT NEW ka.masato.twitter.twitterwordcloud.domain.wordcount.model.WordCountSummary(w.word.wordId,w.word,SUM(w.count))" +
            "FROM WordCounts w WHERE w.time BETWEEN :startTime AND :endTime GROUP BY w.word")
    List<WordCountSummary> sumCountByPeriod(@Param("startTime") LocalDateTime start, @Param("endTime") LocalDateTime end);

    @Query(value = "SELECT NEW ka.masato.twitter.twitterwordcloud.domain.wordcount.model.WordCountSummary(w.word.wordId,w.word,SUM(w.count)) " +
            "FROM WordCounts w WHERE w.word.word = :word GROUP BY w.word")
    WordCountSummary sumCountByWord(@Param("word") String word);

    @Query(value= "SELECT NEW ka.masato.twitter.twitterwordcloud.domain.wordcount.model.WordCountSummary(w.word.wordId,w.word,SUM(w.count)) " +
            "FROM WordCounts w GROUP BY w.word")
    List<WordCountSummary> sumCountAll();
}
