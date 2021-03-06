package ka.masato.twitter.twitterwordcloud.domain.wordcount.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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
    @ManyToOne
    Word word;
    Long count;
    @JsonFormat(pattern="yyyy-MM-dd-HH:mm:ss")
    LocalDateTime time;
}
