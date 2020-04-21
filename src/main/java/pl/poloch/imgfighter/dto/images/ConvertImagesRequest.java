package pl.poloch.imgfighter.dto.images;


public class ConvertImagesRequest {
    private String format = "jpg";
    private String bgcolor = "white";
    private String url;

    public ConvertImagesRequest(){
    }

    public ConvertImagesRequest(String url) {
        this.url = url;
    }

    public ConvertImagesRequest(String format, String url) {
        this.format = format;
        this.url = url;
    }

    public ConvertImagesRequest(String format, String url, String bgcolor) {
        this.format = format;
        this.bgcolor = bgcolor;
        this.url = url;
    }


    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
