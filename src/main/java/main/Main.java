package main;

import model.*;
import util.Input;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static List<User> users;

    public static void main(String[] args) {
        users = createUsers();

        menu();
    }

    //region Create User
    private static List<User> createUsers() {
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

        try {

            Post post1 = new Post(LocalDateTime.now(), paco, new PostText("My first post!"));
            paco.getPosts().add(post1);
            Thread.sleep(100);

            Post post2 = new Post(LocalDateTime.now(), paco, new PostText("My 2nd post!"));
            paco.getPosts().add(post2);
            Thread.sleep(100);

            Post post3 = new Post(LocalDateTime.now(), paco, new PostText("My 3rd post!"));
            paco.getPosts().add(post3);
            Thread.sleep(100);

            Post post4 = new Post(LocalDateTime.now(), paco, new PostText("My 4th post!"));
            paco.getPosts().add(post4);
            Thread.sleep(100);

            Post post5 = new Post(LocalDateTime.now(), paco, new PostText("My 5th post!"));
            paco.getPosts().add(post5);
            Thread.sleep(100);

            Post post6 = new Post(LocalDateTime.now(), paco, new PostText("My 6th post!"));
            paco.getPosts().add(post6);
            Thread.sleep(100);

            Post post7 = new Post(LocalDateTime.now(), paco, new PostText("My 7th post!"));
            paco.getPosts().add(post7);
            Thread.sleep(100);

            Post post8 = new Post(LocalDateTime.now(), paco, new PostText("My 8th post!"));
            paco.getPosts().add(post8);
            Thread.sleep(100);

            Post post9 = new Post(LocalDateTime.now(), paco, new PostText("My 9th post!"));
            paco.getPosts().add(post9);
            Thread.sleep(100);

            Post post10 = new Post(LocalDateTime.now(), paco, new PostText("My 10th post!"));
            paco.getPosts().add(post10);
            Thread.sleep(100);

            Post post11 = new Post(LocalDateTime.now(), paco, new PostText("My 11th post!"));
            paco.getPosts().add(post11);
            Thread.sleep(100);

            Post post12 = new Post(LocalDateTime.now(), paco, new PostText("My 12th post!"));
            paco.getPosts().add(post12);
            Thread.sleep(100);

            Comment comment1 = new Comment(LocalDateTime.now(), "That's a great post!", conchi);
            post1.addComment(comment1);

            Comment comment2 = new Comment(LocalDateTime.now(), "This is getting repetitive", conchi);
            post5.addComment(comment2);


            Comment comment3 = new Comment(LocalDateTime.now(), "/unfollow", conchi);
            post12.addComment(comment3);
        } catch (Exception ex) {}

        return users;
    }
    //endregion

    //region Menu
    private static void menu(){
        int option;

        do {
            System.out.println("----------------------------------------");
            System.out.println("Select an option:");
            System.out.println("1.- Add an user.");
            System.out.println("2.- Select an user.");
            System.out.println("3.- Salir.");
            option = Input.integer();

            switch (option) {

                case 1: addUser();
                    break;

                case 2:
                        boolean repeat;
                        do {
                            repeat = userActions(selectUser());
                        } while (repeat);
                    break;
            }
        } while (option != 3);
    }
    //endregion

    //region Add user
    private static void addUser(){
        User user = new User(Input.string("Type the name of the user:\n"));

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
    private static boolean userActions(User user){
        int option;

        do {
            System.out.println("----------------------------------------");
            System.out.println("1.- See last posts from users followed.");
            System.out.println("2.- Suggested users to follow.");
            System.out.println("3.- Add a post.");
            System.out.println("4.- Add a comment.");
            System.out.println("5.- Unfollow an user.");
            System.out.println("6.- Follow an user.");
            System.out.println("7.- List all posts from this user.");
            System.out.println("8.- List all comments from this user.");
            System.out.println("9.- Delete posts from this user.");
            System.out.println("10.- Delete comments from this user.");
            System.out.println("11.- Select another user.");
            System.out.println("12.- Exit.");
            option = Input.integer();

            switch (option) {

                case 1: getLastPosts(user);
                    break;

                case 2: usersSuggestions(user);
                    break;

                case 3: addPost(user);
                    break;

                case 4: addComment(user);
                    break;

                case 5: unfollowUser(user);
                    break;

                case 6: followUser(user);
                    break;

                case 7: listPostsFromUser(user);
                    break;

                case 8: listCommentsFromUser(user);
                    break;

                case 9: deletePostByUser(user);
                    break;

                case 10: deleteCommentByUser(user);
                    break;

                case 11: return true;

                case 12:
                    break;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (option != 12);

        return false;

    }
    //endregion

    //region Get last posts

    /**
     * Displays the latest (ordered by date) 10 posts the user can see from among the people they follows
     * @param user
     */
    private static void getLastPosts(User user){
        List<Post> availablePost = new ArrayList<>();

        System.out.println("----------------------------------------");
        System.out.println("Feed for " + user.getName());
        System.out.println("----------------------------------------");

        for (User following : user.getFollowing()){
            availablePost.addAll(following.getPosts());
        }

        List<Post> sortedPosts = availablePost.stream()
                .sorted(Comparator.comparing(Post::getPublicationdate).reversed())
                .collect(Collectors.toList());

        for (int i = 0; i < 10 && i < sortedPosts.size(); i++){
            System.out.println(sortedPosts.get(i));
        }
    }
    //endregion

    //region Users Suggestions
    private static void usersSuggestions(User user){
        System.out.println("Suggested users to follow:");

        List<User> suggestedUsers = new ArrayList<>();

        for (User u : user.getFollowing()){
            suggestedUsers.addAll(u.getFollowing());
        }

        for (User u : suggestedUsers){
            System.out.println(u.getName());
        }
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
                int width = Input.integer("Write the width of the image:\n");
                int height = Input.integer("Write the height of the image:\n");

                pc = new PostImage(imageTitle, width, height);

                break;

            case 3:
                String videoTitle = Input.string("Write the title of the video:\n");
                int quality = Input.integer("Write the quality of the video:\n");
                int length = Input.integer("Write the length of the video:\n");

                pc = new PostVideo(videoTitle, quality, length);

                break;
        }

        Post post = new Post(LocalDateTime.now(), user, pc);

        user.makePost(post);
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
    private static void listPostsFromUser(User user) {
        for (Post p : user.getPosts()){
            System.out.println(p);
        }
    }
    //endregion

    //region List Comments
    private static void listCommentsFromUser(User user){
        List<Comment> commentsFromUser = new ArrayList<>();

        for (User u : users){
            for (Post p : u.getPosts()){
                for (Comment c : p.getComments()) {
                    if (c.getOwner().getName().equals(user.getName())){
                        commentsFromUser.add(c);
                    }
                }
            }
        }

        System.out.println("----------------------------------------");
        System.out.println("Comments from user: " + user.getName());
        System.out.println("----------------------------------------");
        for (Comment c : commentsFromUser){
            System.out.println(c.toString());
        }
    }
    //endregion

    //region Delete post by user
    private static void deletePostByUser(User user){
        user.removePosts();
    }
    //endregion

    //region Delete comments by user
    private static void deleteCommentByUser(User user){
        for (User u : users) {
            for (Post p : u.getPosts()){
                p.getComments().removeIf(c -> c.getOwner().getName().equals(user.getName()));
            }
        }
    }
    //endregion

    //endregion

}
