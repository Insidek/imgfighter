package pl.poloch.imgfighter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public static String logsDirectory;
    public static String imagesConvertedDirectory;

    Logger logger = LoggerFactory.getLogger(ImgFighterApplication.class);

    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        Instant nowUtc = Instant.now();
        ZoneId europeWarsaw = ZoneId.of("Europe/Warsaw");
        logger.info("TimeZone is: " + europeWarsaw.toString());

        // Setting working directory
        workingDirectory = System.getProperty("user.dir");
        logger.info("WorkingDirectory is: " + workingDirectory);

        // Create ./images/ directory in workingDirectory
        File dirImages = new File(workingDirectory + "/images");
        boolean dirImagesExists = dirImages.mkdirs();
        imagesDirectory = workingDirectory + "/images/";
        logger.info("ImagesDirectory is: " + imagesDirectory);

        // Create ./images/converted/ directory in workingDirectory
        File dirImagesConverted = new File(workingDirectory + "/images/converted");
        boolean dirImagesConvertedExists = dirImagesConverted.mkdirs();
        imagesConvertedDirectory = workingDirectory + "/images/converted/";
        logger.info("ImagesDirectory is: " + imagesConvertedDirectory);

        // Create ./logs/ directory in workingDirectory
        File dirLogs = new File(workingDirectory + "/logs");
        boolean dirLogsExists = dirLogs.mkdirs();
        logsDirectory = workingDirectory + "/logs";
        logger.info("LogsDirectory is: " + logsDirectory);
    }



    public static void main(String[] args) {
        SpringApplication.run(ImgFighterApplication.class, args);

    }

}
