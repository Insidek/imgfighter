package pl.poloch.imgfighter.services.images;

import org.apache.commons.io.FilenameUtils;
import pl.poloch.imgfighter.ImgFighterApplication;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class ConvertImagesController {
    private String input;
    private String output;
    private String format;
    private String bgcolor;


    public ConvertImagesController(String input) {
        this.input = input;
        this.format = "jpg";
        this.output = ImgFighterApplication.imagesConvertedDirectory + FilenameUtils.getBaseName(input) + "." + format;
        this.bgcolor = "white";
    }

    public ConvertImagesController(String input, String format) {
        this.input = input;
        this.format = format;
        this.output = ImgFighterApplication.imagesConvertedDirectory + FilenameUtils.getBaseName(input) + "." + format;
        this.bgcolor = "white";
    }

    public ConvertImagesController(String input, String format, String bgcolor) {
        this.input = input;
        this.format = format;
        this.output = ImgFighterApplication.imagesConvertedDirectory + FilenameUtils.getBaseName(input) + "." + format;
        this.bgcolor = bgcolor;

    }

    public String convert() throws IOException, NoSuchFieldException, IllegalAccessException {
        Color color;
        Field field = Color.class.getField(bgcolor);
        color = (Color)field.get(null);

        File file = new File(input);
        BufferedImage image = ImageIO.read(file);
        BufferedImage result;
        if (format.equals("png") || format.equals("tiff")) {
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
        ImageIO.write(result, format, new File(output));


        Boolean delete = file.delete();
        return output;
    }
}
