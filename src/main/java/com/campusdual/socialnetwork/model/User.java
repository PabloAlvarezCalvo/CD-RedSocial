package com.campusdual.socialnetwork.model;

import com.campusdual.socialnetwork.model.content.Post;

import java.util.ArrayList;
import java.util.List;

public class User {
    //Contendrá nombre, una lista de los usuarios a los que sigue, lista de posts.
    //private long id;
    private String name;
    private List<User> following = new ArrayList<>();
    private List<Post> posts = new ArrayList<>();

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

    public void addFollowing(User u){
        this.following.add(u);
    }

    public void removeFollowing(User u) {
        this.following.remove(u);
    }

    public List<Post> getPosts() {
        return posts;
    }
    public void makePost(Post p){
        posts.add(p);
    }
    public void removePost(Post postToDelete){
        for (Post p : posts){
            if (p == postToDelete){
                if (p.getComments().size() > 0){
                    p.removeAllComments();
                }
            }
        }
        posts.removeIf(p -> p == postToDelete);
    }
    public void removePosts(){
        for (Post p : this.getPosts()){
            removePost(p);
        }
    }

    public List<Post> getFollowingPosts(){
        List<Post> followingPosts = new ArrayList<>();

        for (User u : following) {
            followingPosts.addAll(u.getPosts());
        }
            return followingPosts;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", following=" + following +
                ", posts=" + posts +
                '}';
    }
}
