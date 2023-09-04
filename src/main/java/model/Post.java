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
    private PostContent postContent;
    private List<Comment> comments;

    public Post(LocalDateTime publicationdate, PostContent postContent) {
        this.publicationdate = publicationdate;
        this.postContent = postContent;
    }

    public LocalDateTime getPublicationdate() {
        return publicationdate;
    }

    public void setPublicationdate(LocalDateTime publicationdate) {
        this.publicationdate = publicationdate;
    }

    public PostContent getPostContent() {
        return postContent;
    }

    public void setPostContent(PostContent postContent) {
        this.postContent = postContent;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment c){
        this.comments.add(c);
    }

    public void removeComment(Comment c) {
        this.comments.remove(c);
    }
}
