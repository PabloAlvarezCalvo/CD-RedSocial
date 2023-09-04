package model;

public class PostVideo extends PostContent {
    private String title;
    private int quality;
    private int length;

    public PostVideo(String title, int quality, int length) {
        this.title = title;
        this.quality = quality;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
