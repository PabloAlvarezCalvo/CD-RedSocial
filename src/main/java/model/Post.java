package model;

import java.time.LocalDateTime;
import java.util.List;

public class Post {
    /*
     * Contendrá fecha y lista de comentarios.
     * Además, podrá ser un texto (que deberá tener un String con el contenido),
     * una imagen (deberá tener un título y dimensiones)
     * o un vídeo (que tendrá título, calidad y duración en segundos).
     */

    private LocalDateTime publicationdate;
    private List<Post> comments;
    private String text;
    private Image image;
    private Video video;

    public Post(LocalDateTime publicationdate) {
        this.publicationdate = publicationdate;
    }

    public LocalDateTime getPublicationdate() {
        return publicationdate;
    }

    public void setPublicationdate(LocalDateTime publicationdate) {
        this.publicationdate = publicationdate;
    }

    public List<Post> getComments() {
        return comments;
    }

    public String getText() {
        return text;
    }

    public Image getImage() {
        return image;
    }

    public Video getVideo() {
        return video;
    }
}
