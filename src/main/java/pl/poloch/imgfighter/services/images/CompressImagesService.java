package pl.poloch.imgfighter.services.images;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.poloch.imgfighter.ImgFighterApplication;
import pl.poloch.imgfighter.dto.images.CompressImageRequest;

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

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class CompressImagesService {
    private String output;
    private String format;

    Logger logger = LoggerFactory.getLogger(CompressImagesService.class);


    public ResponseEntity<byte[]> compress(CompressImageRequest compressImageRequest) throws IOException {
        output = ImgFighterApplication.imagesConvertedDirectory + FilenameUtils.getName(compressImageRequest.getUrl());

        File input = new File(ImagesService.downloadImage(compressImageRequest.getUrl()));
        BufferedImage image = ImageIO.read(input);

        format = FilenameUtils.getExtension(input.toString());

        File compressedImageFile = new File(output);
        OutputStream os = new FileOutputStream(compressedImageFile);

        Iterator<ImageWriter> writers =  ImageIO.getImageWritersByFormatName(format);
        ImageWriter writer = (ImageWriter) writers.next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();

        if (format.equals("jpg") || format.equals("jpeg")) {
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(compressImageRequest.getQuality());
        }
        else {
            throw new UnsupportedOperationException("Compression for " + format + " extension not supported");
        }
        writer.write(null, new IIOImage(image, null, null), param);

        os.close();
        ios.close();
        writer.dispose();

        Boolean delete = input.delete();
        return ImagesService.uploadImage(output);
    }
}
