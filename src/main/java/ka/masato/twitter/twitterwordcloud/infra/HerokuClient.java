package ka.masato.twitter.twitterwordcloud.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Slf4j
@Component
public class HerokuClient {

    private RestTemplate restTemplate;
    @Value("${heroku.own.url}")
    private String getUrl;

    public HerokuClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void getHerokuOwn() {
        try {
            URI url = new URI(getUrl);
            restTemplate.getForObject(url, String.class);
            log.info("Hey Dyno Please do not sleep.");
        } catch (URISyntaxException e) {
            log.error("Failed setting URL for heroku.own.url.");
        }
    }
}
