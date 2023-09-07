package com.campusdual.socialnetwork.model.content.post_type;

import com.campusdual.socialnetwork.model.content.PostContent;

public class VideoPost extends PostContent {
    private int quality;
    private int length;

    public VideoPost(String title, int quality, int length) {
        super(title);
        this.quality = quality;
        this.length = length;
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

    @Override
    public String toString() {
        return "Video. " +
                "Title: " + super.getTitle() +
                ", quality: " + quality +
                ", length: " + length / 3600 + ":" + ((length % 3600) / 60) + ":" + length % 60;
    }
}
