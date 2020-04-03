package pl.poloch.imgfighter.controllers.images;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.poloch.imgfighter.ImgFighterApplication;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;



public class CompressImagesController {
    private Float quality = 0.8f;
    private String downloadedFilePath;
    private String format;
    private String fileName;

    Logger logger = LoggerFactory.getLogger(CompressImagesController.class);


    public CompressImagesController(String downloadedFilePath) {
        this.downloadedFilePath = downloadedFilePath;
        this.fileName = FilenameUtils.getName(downloadedFilePath);
        this.format = FilenameUtils.getExtension(fileName);
    }

    public CompressImagesController(String downloadedFilePath, Float quality) {
        this.quality = quality;
        this.downloadedFilePath = downloadedFilePath;
        this.fileName = FilenameUtils.getName(downloadedFilePath);
        this.format = FilenameUtils.getExtension(fileName);
    }

    public CompressImagesController(String downloadedFilePath, String format) {
        this.downloadedFilePath = downloadedFilePath;
        this.fileName = FilenameUtils.getName(downloadedFilePath);
        this.format = format;
    }

    public CompressImagesController(String downloadedFilePath, Float quality, String format) {
        this.quality = quality;
        this.downloadedFilePath = downloadedFilePath;
        this.fileName = FilenameUtils.getName(downloadedFilePath);
        this.format = format;
    }


    public String compress() throws IOException {
        String convertedFilePath = ImgFighterApplication.imagesConvertedDirectory + fileName;

        File input = new File(downloadedFilePath);
        BufferedImage image = ImageIO.read(input);

        File compressedImageFile = new File(convertedFilePath);
        OutputStream os = new FileOutputStream(compressedImageFile);

        Iterator<ImageWriter> writers =  ImageIO.getImageWritersByFormatName(format);
        ImageWriter writer = (ImageWriter) writers.next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();

        if (format.equals("jpg") || format.equals("jpeg")) {
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality);
        }
        else {
            throw new UnsupportedOperationException("Compression for " + format + " extension not supported");
        }
        writer.write(null, new IIOImage(image, null, null), param);

        os.close();
        ios.close();
        writer.dispose();

        Boolean delete = input.delete();
        return convertedFilePath;
    }
}
