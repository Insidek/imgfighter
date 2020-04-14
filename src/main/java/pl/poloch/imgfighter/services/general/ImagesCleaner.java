package pl.poloch.imgfighter.services.general;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pl.poloch.imgfighter.ImgFighterApplication;

import java.io.File;
import java.util.Date;

@Configuration
@EnableScheduling
public class ImagesCleaner {

    Integer hours = 2;
    long daysToMillis = new Date().getTime() - hours * 1 * 60 * 60 * 1000;

    Logger logger = LoggerFactory.getLogger(ImagesCleaner.class);

    @Scheduled(fixedDelay = 600000)
    public void deleteFilesOlderThanNDays() {
        logger.info("Annihilation of files older than " + hours + " hours");
        File toAnilhilation = new File(ImgFighterApplication.imagesConvertedDirectory);

        if (toAnilhilation.isDirectory()) {
            for (File f : toAnilhilation.listFiles()) {
                long diff = new Date().getTime() - f.lastModified();
                if (diff > hours * 24 * 60 * 60 * 1000) {
                    f.delete();
                    logger.info("Annihilated: " + f.getAbsolutePath());
                }
            }
        }
    }

}