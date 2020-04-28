package pl.poloch.imgfighter.dto.images;


public class CompressImageRequest {
    private Float quality = 0.8f;
    private String url;

    public Float getQuality() {
        return quality;
    }

    public void setQuality(Float quality) {
        this.quality = quality;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
