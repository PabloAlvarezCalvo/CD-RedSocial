package model.content.post_type;

import model.content.PostContent;

public class TextPost extends PostContent {
    private String text;

    public TextPost(String title, String text) {
        super(title);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Title: " + super.getTitle() + "\nText: \n" + text;
    }
}
