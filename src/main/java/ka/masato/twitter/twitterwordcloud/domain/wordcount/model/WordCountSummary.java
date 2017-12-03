package ka.masato.twitter.twitterwordcloud.domain.wordcount.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordCountSummary {

    @Id
    @GeneratedValue
    Integer Id;
    @OneToOne
    @NotNull
    Word word;
    @NotNull
    Long count;

}
