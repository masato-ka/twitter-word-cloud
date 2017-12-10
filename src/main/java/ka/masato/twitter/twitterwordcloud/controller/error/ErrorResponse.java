package ka.masato.twitter.twitterwordcloud.controller.error;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private Integer errorCode;
    private String errorMessage;

}
