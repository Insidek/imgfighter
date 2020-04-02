package pl.poloch.imgfighter.controllers.images;

import org.apache.commons.io.FilenameUtils;
import pl.poloch.imgfighter.ImgFighterApplication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.LocalDateTime;

public class ImagesController {
    public static String  downloadImages(String url) throws IOException {
        String imagesPath = ImgFighterApplication.imagesDirectory + FilenameUtils.getName(url) + System.currentTimeMillis();
        URL urlToDownload = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(urlToDownload.openStream());
        FileOutputStream fos = new FileOutputStream(imagesPath);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
        return imagesPath;
    }
}
