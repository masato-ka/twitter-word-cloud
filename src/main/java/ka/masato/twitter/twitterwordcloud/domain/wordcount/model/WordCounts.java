package ka.masato.twitter.twitterwordcloud.domain.wordcount.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordCounts implements Serializable {

    @Id
    @GeneratedValue
    Integer id;
    //TODO
    String word;
    Integer count;
}
