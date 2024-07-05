package in.project.Activelife.models_1.image_model;

public class SmallImage {
    private  String url;
    private int width;
    private int height;
    public SmallImage(String url,int width, int height){
        this.url=url;
        this.width=width;
        this.height=height;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}