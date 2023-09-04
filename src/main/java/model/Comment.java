package model;

import java.time.LocalDateTime;

public class Comment {
    private LocalDateTime publicationDate;
    private String text;
    private User owner;

    public Comment(LocalDateTime publicationDate, String text, User owner) {
        this.publicationDate = publicationDate;
        this.text = text;
        this.owner = owner;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
