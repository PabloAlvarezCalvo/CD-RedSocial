package main;

import model.*;
import util.Input;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<User> users;
    private static List<Post> posts = new ArrayList<>();
    private static List<Comment> comments = new ArrayList<>();

    public static void main(String[] args) {
        users = createUsers();

        menu();
    }

    //region Create User
    private static List<User> createUsers(){
        List<User> users = new ArrayList<>();

        User paco = new User("Paco");
        User conchi = new User("Conchi");
        User alex = new User("Alex");

        paco.addFollowing(alex);

        conchi.addFollowing(paco);
        conchi.addFollowing(alex);

        alex.addFollowing(conchi);

        users.add(paco);
        users.add(conchi);
        users.add(alex);

        Post post1 = new Post(LocalDateTime.now(), paco, new PostText("My first post!"));
        paco.getPosts().add(post1);
        posts.add(post1);

        Comment comment1 = new Comment(LocalDateTime.now(), "That's a great post!", conchi);
        post1.addComment(comment1);
        comments.add(comment1);

        return users;
    }
    //endregion

    //region Menu
    private static void menu(){
        int option;

        do {
            System.out.println("Select an option:");
            System.out.println("1.- Add an user.");
            System.out.println("2.- Select an user.");
            System.out.println("3.- Salir.");
            option = Input.integer();

            switch (option) {

                case 1: addUser();
                    break;

                case 2: userActions(selectUser());
                    break;
            }
        } while (option != 9);
    }
    //endregion

    //region Add user
    private static void addUser(){
        User user = new User(Input.string("Type the name of the user"));

        users.add(user);

    }
    //endregion

    //region Select User
    private static User selectUser(){
        for(User u : users) {
            System.out.println(u.getName());
        }

        String userName = Input.string("Type the name of the user:\n");

        for (User u : users) {
            if (u.getName().equals(userName)){
                return u;
            }
        }

        return null;

    }
    //endregion

    //region User Actions
    //region User Actions Menu
    private static void userActions(User user){
        int option;

        do {
            System.out.println("1.- Add a post.");
            System.out.println("2.- Add a comment.");
            System.out.println("3.- Unfollow an user.");
            System.out.println("4.- Follow an user.");
            System.out.println("5.- List all posts from an user.");
            System.out.println("6.- List all comments from an user.");
            System.out.println("7.- Show number of comments in a post.");
            System.out.println("8.- Delete comments from this user.");
            System.out.println("9.- Delete posts from this user.");
            System.out.println("10.- Exit");
            option = Input.integer();

            switch (option) {

                case 1: addPost(user);
                    break;

                case 2: addComment(user);
                    break;
                case 3: unfollowUser(user);
                    break;

                case 4: followUser(user);
                    break;

                case 5: listPostsFromUser(user);
                    break;

                case 6: listCommentsFromUser(user);
                    break;

                case 7: showCommentCount();
                    break;

                case 8: deletePostByUser(user);
                    break;

                case 9: deleteCommentByUser(user);
                    break;

                case 10:
                    break;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (option != 10);

    }
    //endregion

    //region Add Post
    private static void addPost(User user){
        System.out.println("Select the type of post:");
        System.out.println("1. Text.");
        System.out.println("2. Image.");
        System.out.println("3. Video.");
        int option = Input.integer();

        PostContent pc = null;

        switch (option){
            case 1:
                pc = new PostText(Input.string("Write the text of the post:\n"));

                break;

            case 2:
                String imageTitle = Input.string("Write the title of the image:\n");
                int width = Input.integer("Write the width of the image:");
                int height = Input.integer("Write the height of the image:");

                pc = new PostImage(imageTitle, width, height);

                break;

            case 3:
                String videoTitle = Input.string("Write the title of the video:\n");
                int quality = Input.integer("Write the quality of the video:\n");
                int length = Input.integer("Write the length of the video:\n");

                PostVideo video = new PostVideo(videoTitle, quality, length);

                break;
        }

        Post post = new Post(LocalDateTime.now(), user, pc);

        user.makePost(post);
        posts.add(post);
    }
    //endregion

    //region Add Comment
    public static void addComment(User user){
        List<Post> availablePosts = user.getFollowingPosts();
        availablePosts.addAll(user.getPosts());

        System.out.println("----------------------------------------");
        System.out.println("Posts from the users " + user.getName() + " follows.");
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");

        for (int i = 0; i < availablePosts.size(); i++){
            System.out.println("[" + i + "] " + availablePosts.get(i).toString());
            System.out.println("----------------------------------------");
        }

        int postIndex = Input.integer("Type the index of the post to reply to:\n");
        Post postToReply = availablePosts.get(postIndex);

        Comment comment = new Comment(LocalDateTime.now(), Input.string("Type your comment:\n"), user);
        postToReply.addComment(comment);
    }
    //endregion

    //region Unfollow User
    private static void unfollowUser(User user){
        for (User u : user.getFollowing()){
            System.out.println(u.getName());
        }

        String stringUserToUnfollow = Input.string("Write the name of the user to unfollow:\n");
        User userToUnfollow = null;
        for (User u : user.getFollowing()){
            if (u.getName().equals(stringUserToUnfollow)){
                userToUnfollow = u;
            }
        }

        user.removeFollowing(userToUnfollow);
    }
    //endregion

    //region Follow User
    private static void followUser(User user){
        for (User u : users){
            System.out.println(u.getName());
        }

        String userToFollow = Input.string("Write the name of the user to follow:\n");
        for (User u : users){
            if (u.getName().equals(userToFollow)){
                user.addFollowing(u);
            }
        }
    }
    //endregion

    //region List Posts
    private static void listPostsFromUser(User u) {
        for (Post p : u.getPosts()){
            System.out.println(p);
        }
    }
    //endregion

    //region List Comments
    private static void listCommentsFromUser(User u){
        for(Comment c : comments){
            if (c.getOwner().getName().equals(u.getName())){
                System.out.println(c);
            }
        }
    }
    //endregion

    //region Show comment count
    private static void showCommentCount(){

    }
    //endregion

    //endregion

    private static void deletePostByUser(User u){
        for (Post p : u.getPosts()){
            posts.remove(p);
        }

        u.removePosts();
    }

    private static void deleteCommentByUser(User u){
        comments.removeIf(c -> c.getOwner() == u);
    }

}
