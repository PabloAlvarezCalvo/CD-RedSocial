package model;

public class PostText extends PostContent {
    private String text;

    public PostText(String text) {
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
        return "Text: \n" + text;
    }
}
