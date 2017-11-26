package ka.masato.twitter.twitterwordcloud.domain.wordcount.repository;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.WordCounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordCountsRepository extends JpaRepository<WordCounts, Integer> {

    WordCounts findByWord(String word);
}
