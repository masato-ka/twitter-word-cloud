package ka.masato.twitter.twitterwordcloud.domain.wordcount.repository;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer>{

    Word findByWord(String word);

}
