package model;

import java.util.List;

public class User {
    //Contendrá nombre, una lista de los usuarios a los que sigue, lista de posts.
    //private long id;
    private String name;
    private List<User> following;
    private List<Post> publications;

    public User(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getFollowing() {
        return following;
    }

    public List<Post> getPublications() {
        return publications;
    }


}
