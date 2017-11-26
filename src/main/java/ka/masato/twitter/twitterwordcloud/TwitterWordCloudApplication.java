package ka.masato.twitter.twitterwordcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TwitterWordCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterWordCloudApplication.class, args);
	}
}
