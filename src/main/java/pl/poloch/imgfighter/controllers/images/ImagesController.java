package pl.poloch.imgfighter.controllers.images;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.*;
import pl.poloch.imgfighter.ImgFighterApplication;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class ImagesController {
    public static String downloadImage(String url) throws IOException {
        String imagesPath = ImgFighterApplication.imagesDirectory + System.currentTimeMillis() + FilenameUtils.getName(url);
        URL urlToDownload = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(urlToDownload.openStream());
        FileOutputStream fos = new FileOutputStream(imagesPath);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
        return imagesPath;
    }

    public static ResponseEntity<byte[]> uploadImage(String convertedFilePath) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        FileInputStream in = new FileInputStream(convertedFilePath);
        byte[] media = IOUtils.toByteArray(in);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        in.close();

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
    }
}
