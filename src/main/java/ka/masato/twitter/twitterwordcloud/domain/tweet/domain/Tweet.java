package ka.masato.twitter.twitterwordcloud.domain.tweet.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {

    private String text;
    private Date createTime;

}
