package ka.masato.twitter.twitterwordcloud.domain.wordcount.repository;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.WordCounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WordCountRepository extends JpaRepository<WordCounts, Integer> {
    List<WordCounts> findByWord(String word);
    List<WordCounts> findByTimeBetween(LocalDateTime time, LocalDateTime time2);

}
