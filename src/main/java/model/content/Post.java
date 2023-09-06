package model.content;

import model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Post {
    /*
     * Contendrá fecha y lista de comentarios.
     * Además, podrá ser un texto (que deberá tener un String con el contenido),
     * una imagen (deberá tener un título y dimensiones)
     * o un vídeo (que tendrá título, calidad y duración en segundos).
     */

    private User owner;

    private LocalDateTime publicationdate;
    private PostContent postContent;
    private List<Comment> comments = new ArrayList<>();

    public Post(LocalDateTime publicationdate, User owner, PostContent postContent) {
        this.publicationdate = publicationdate;
        this.owner = owner;
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

    public void removeAllComments(){ this.comments = new ArrayList<>(); }

    public int getCommentCount(){
        return comments.size();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String commentsString = comments.stream().map(Object::toString)
                .collect(Collectors.joining("\n"));

        return "Post\n" +
                "(" + publicationdate.format(formatter) + ") " +
                owner.getName() + "\n" +
                postContent + "\n" +
                getCommentCount() + " comment/s.\n" +
                commentsString;
    }
}
