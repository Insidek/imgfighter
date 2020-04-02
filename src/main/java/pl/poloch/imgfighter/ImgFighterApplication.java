package pl.poloch.imgfighter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.io.File;
import java.time.Instant;
import java.time.ZoneId;


@SpringBootApplication
@EnableJpaAuditing
public class ImgFighterApplication {
    public static String workingDirectory;
    public static String imagesDirectory;

    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        Instant nowUtc = Instant.now();
        ZoneId europeWarsaw = ZoneId.of("Europe/Warsaw");

        // Setting working directory
        workingDirectory = System.getProperty("user.dir");

        // Create images directory in workingDirectory
        File dir = new File(workingDirectory + "/images");
        boolean dirExists = dir.mkdirs();

        // Set imagesDirectory
        imagesDirectory = workingDirectory + "/images/";
    }



    public static void main(String[] args) {
        SpringApplication.run(ImgFighterApplication.class, args);

    }

}
