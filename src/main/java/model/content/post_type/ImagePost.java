package model.content.post_type;

import model.content.PostContent;

public class ImagePost extends PostContent {
    private int width;
    private int height;

    public ImagePost(String title, int width, int height) {
        super(title);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Image. " +
                "Title: " + super.getTitle() +
                ", width: " + width +
                ", height: " + height;
    }
}
