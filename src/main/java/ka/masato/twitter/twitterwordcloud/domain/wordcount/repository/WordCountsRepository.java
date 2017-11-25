package ka.masato.twitter.twitterwordcloud.domain.wordcount.repository;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.WordCounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordCountsRepository extends JpaRepository<Integer, WordCounts> {

    public WordCounts findByWord(String word);
}
