package com.campusdual.socialnetwork.main.model.content;

import com.campusdual.socialnetwork.main.model.User;

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

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private User owner;

    private LocalDateTime publicationDate;
    private PostContent postContent;
    private List<Comment> comments = new ArrayList<>();

    public Post(LocalDateTime publicationDate, User owner, PostContent postContent) {
        this.publicationDate = publicationDate;
        this.owner = owner;
        this.postContent = postContent;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
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

        String commentsString = comments.stream().map(Object::toString)
                .collect(Collectors.joining("\n"));

        return "Post\n" +
                "(" + publicationDate.format(formatter) + ") " +
                owner.getName() + "\n" +
                postContent + "\n" +
                getCommentCount() + " comment/s.\n" +
                commentsString;
    }
}
