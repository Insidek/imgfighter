package pl.poloch.imgfighter.services.images;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.poloch.imgfighter.ImgFighterApplication;
import pl.poloch.imgfighter.dto.images.ConvertImagesRequest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

@Service
public class ConvertImagesService {

    private String output;

    public ResponseEntity<byte[]> convert(ConvertImagesRequest convertImagesRequest) throws IOException, NoSuchFieldException, IllegalAccessException {
        Color color;
        Field field = Color.class.getField(convertImagesRequest.getBgcolor());
        color = (Color)field.get(null);

        File file = new File(ImagesService.downloadImage(convertImagesRequest.getUrl()));
        BufferedImage image = ImageIO.read(file);
        BufferedImage result;
        if (convertImagesRequest.getFormat().equals("png") || convertImagesRequest.getFormat().equals("tiff")) {
            result = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            result.createGraphics().drawImage(image, 0, 0, null);
        } else {
            result = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            result.createGraphics().drawImage(image, 0, 0, color, null);
        }
        output = ImgFighterApplication.imagesConvertedDirectory + FilenameUtils.getBaseName(file.toString()) + "." + convertImagesRequest.getFormat();
        ImageIO.write(result, convertImagesRequest.getFormat(), new File(output));


        Boolean delete = file.delete();
        return ImagesService.uploadImage(output);
    }

}
