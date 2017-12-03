package ka.masato.twitter.twitterwordcloud.domain.wordcount.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word {

    @Id
    @GeneratedValue
    Integer wordId;
    @NotNull
    String word;
    String partOfSpeech;
    @JsonIgnore
    @OneToMany(mappedBy="word")
    List<WordCounts> wordCountsList;
}

