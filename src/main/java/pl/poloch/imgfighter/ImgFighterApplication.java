package pl.poloch.imgfighter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.ZoneId;


@SpringBootApplication
@EnableJpaAuditing
public class ImgFighterApplication {

    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        Instant nowUtc = Instant.now();
        ZoneId europeWarsaw = ZoneId.of("Europe/Warsaw");
    }

    public static void main(String[] args) {
        SpringApplication.run(ImgFighterApplication.class, args);

    }

}
