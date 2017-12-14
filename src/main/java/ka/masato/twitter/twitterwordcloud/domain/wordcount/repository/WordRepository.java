package ka.masato.twitter.twitterwordcloud.domain.wordcount.repository;

import ka.masato.twitter.twitterwordcloud.domain.wordcount.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer>{
    //TODO WordにはTIMEがない。。。DBのテーブル変更するけど、そのとき元のデータはどうする？
    //List<Word> findByTimeBetween(LocalDateTime start, LocalDateTime end);
    Word findByWord(String word);

}
