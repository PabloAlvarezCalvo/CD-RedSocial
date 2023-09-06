package main;

import model.*;
import model.content.*;
import model.content.post_type.ImagePost;
import model.content.post_type.TextPost;
import model.content.post_type.VideoPost;
import util.Input;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final int MAX_POSTS_VIEW = 10;
    private static List<User> users;
    private static User currentUser = null;

    public static void main(String[] args) {
        users = mockUserDB();
        menu();
    }

    //region Create User
    private static List<User> mockUserDB() {
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

            Post post1 = new Post(LocalDateTime.now(), paco, new TextPost("", "My first post!"));
            paco.getPosts().add(post1);
            Thread.sleep(100);

            Post post2 = new Post(LocalDateTime.now(), paco, new TextPost("", "My 2nd post!"));
            paco.getPosts().add(post2);
            Thread.sleep(100);

            Post post3 = new Post(LocalDateTime.now(), paco, new TextPost("", "My 3rd post!"));
            paco.getPosts().add(post3);
            Thread.sleep(100);

            Post post4 = new Post(LocalDateTime.now(), paco, new TextPost("", "My 4th post!"));
            paco.getPosts().add(post4);
            Thread.sleep(100);

            Post post5 = new Post(LocalDateTime.now(), paco, new TextPost("", "My 5th post!"));
            paco.getPosts().add(post5);
            Thread.sleep(100);

            Post post6 = new Post(LocalDateTime.now(), paco, new TextPost("", "My 6th post!"));
            paco.getPosts().add(post6);
            Thread.sleep(100);

            Post post7 = new Post(LocalDateTime.now(), paco, new TextPost("", "My 7th post!"));
            paco.getPosts().add(post7);
            Thread.sleep(100);

            Post post8 = new Post(LocalDateTime.now(), paco, new TextPost("", "My 8th post!"));
            paco.getPosts().add(post8);
            Thread.sleep(100);

            Post post9 = new Post(LocalDateTime.now(), paco, new TextPost("", "My 9th post!"));
            paco.getPosts().add(post9);
            Thread.sleep(100);

            Post post10 = new Post(LocalDateTime.now(), paco, new TextPost("", "My 10th post!"));
            paco.getPosts().add(post10);
            Thread.sleep(100);

            Post post11 = new Post(LocalDateTime.now(), paco, new TextPost("", "My 11th post!"));
            paco.getPosts().add(post11);
            Thread.sleep(100);

            Post post12 = new Post(LocalDateTime.now(), paco, new TextPost("", "My 12th post!"));
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
            System.out.println("========================================");
            System.out.println("           SOCIAL NETWORK APP           ");
            System.out.println("========================================");
            System.out.println("Select an option:");
            System.out.println("1.- Register.");
            System.out.println("2.- Login.");
            System.out.println("0.- Exit.");
            option = Input.integer();

            switch (option) {

                case 1: addUser();
                    break;

                case 2:
                        boolean repeat;
                        do {
                            selectUser();
                            repeat = userActions();
                        } while (repeat);
                    break;
            }
        } while (option != 0);
    }
    //endregion

    //region Add user
    private static void addUser(){
        boolean correctName = true;
        User newUser = null;
        do {
            String userInputName = Input.string("Type the name of the user:\n");
            for (User u : users) {
                if (u.getName().equals(userInputName)){
                    System.out.println("Error: An user with that name already exists. Choose a different one.");
                    correctName = false;
                    break;
                } else {
                    correctName = true;
                    newUser = new User(userInputName);
                    System.out.println("User: " + userInputName + " created successfully.");
                }
            }

        } while (!correctName);

        users.add(newUser);
    }
    //endregion

    //region Select User
    private static void selectUser(){
        for(User u : users) {
            System.out.println(u.getName());
        }

        String userName = Input.string("Type the name of the user:\n");

        for (User u : users) {
            if (u.getName().equals(userName)){
                currentUser = u;
            }
        }

    }
    //endregion

    //region User Actions
    //region User Actions Menu
    private static boolean userActions(){
        int option;

        if (currentUser == null) {
            return true;
        }

        do {
            System.out.println("----------------------------------------");
            System.out.println("Welcome, " + currentUser.getName());
            System.out.println("----------------------------------------");
            System.out.println("1.- Read your feed.");
            System.out.println("2.- Suggested users to follow.");
            System.out.println("3.- Add a post.");
            System.out.println("4.- Add a comment.");
            System.out.println("5.- Follow an user.");
            System.out.println("6.- Unfollow an user.");
            System.out.println("7.- List all posts from this user.");
            System.out.println("8.- List all comments from this user.");
            System.out.println("9.- Delete posts from this user.");
            System.out.println("10.- Delete comments from this user.");
            System.out.println("11.- Select another user.");
            System.out.println("0.- Exit.");
            option = Input.integer();

            switch (option) {

                case 1: getLastPosts();
                    break;

                case 2: usersSuggestions();
                    break;

                case 3: addPost();
                    break;

                case 4: addComment();
                    break;

                case 5: followUser();
                    break;

                case 6: unfollowUser();
                    break;

                case 7: listPostsFromUser();
                    break;

                case 8: listCommentsFromUser();
                    break;

                case 9: deletePostByUser();
                    break;

                case 10: deleteCommentByUser();
                    break;

                case 11: currentUser = null;
                    return true; //Change user

                case 0: //Exit
                    break;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (option != 0);

        return false;

    }
    //endregion

    //region Get last posts

    /**
     * Displays the latest (ordered by date) 10 posts the user can see from among the people they follows
     */
    private static void getLastPosts(){
        List<Post> availablePost = new ArrayList<>();

        System.out.println("========================================");
        System.out.println("          Feed for " + currentUser.getName());
        System.out.println("========================================");

        for (User following : currentUser.getFollowing()){
            availablePost.addAll(following.getPosts());
        }

        List<Post> sortedPosts = availablePost.stream()
                .sorted(Comparator.comparing(Post::getPublicationDate).reversed())
                .collect(Collectors.toList());

        for (int i = 0; i < MAX_POSTS_VIEW && i < sortedPosts.size(); i++){
            System.out.println(sortedPosts.get(i));
            System.out.println("----------");
        }
    }
    //endregion

    //region Users Suggestions
    private static void usersSuggestions(){
        System.out.println("Suggested users to follow:");

        List<User> suggestedUsers = new ArrayList<>();

        //Making a list of all the accounts followed by the people this user follows
        for (User u : currentUser.getFollowing()){
            suggestedUsers.addAll(u.getFollowing());
        }


        //Removing people the user already follows
        suggestedUsers.removeAll(currentUser.getFollowing());
        suggestedUsers.remove(currentUser);

        for (User u : suggestedUsers){
            System.out.println(u.getName());
        }
    }
    //endregion

    //region Add Post
    private static void addPost(){
        System.out.println("----------------------------------------");
        System.out.println("Select the type of post:");
        System.out.println("1. Text.");
        System.out.println("2. Image.");
        System.out.println("3. Video.");
        System.out.println("----------------------------------------");
        int option = Input.integer();

        PostContent pc = null;

        switch (option){
            case 1:
                pc = new TextPost(Input.string("Write the title of the post:\n"), Input.string("Write the text of the post:\n"));
                break;

            case 2:
                String imageTitle = Input.string("Write the title of the image:\n");
                int width = Input.integer("Write the width of the image:\n");
                int height = Input.integer("Write the height of the image:\n");

                pc = new ImagePost(imageTitle, width, height);

                break;

            case 3:
                String videoTitle = Input.string("Write the title of the video:\n");
                int quality = Input.integer("Write the quality of the video:\n");
                int length = Input.integer("Write the length of the video, in seconds:\n");

                pc = new VideoPost(videoTitle, quality, length);

                break;
        }

        Post post = new Post(LocalDateTime.now(), currentUser, pc);

        currentUser.makePost(post);
    }
    //endregion

    //region Add Comment
    public static void addComment(){
        List<Post> availablePosts = currentUser.getFollowingPosts();
        availablePosts.addAll(currentUser.getPosts());

        System.out.println("----------------------------------------");
        System.out.println("Posts from the users " + currentUser.getName() + " follows.");
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");

        for (int i = 0; i < availablePosts.size(); i++){
            System.out.println("[" + i + "] " + availablePosts.get(i).toString());
            System.out.println("----------------------------------------");
        }

        int postIndex = Input.integer("Type the index of the post to reply to:\n");
        Post postToReply = availablePosts.get(postIndex);

        Comment comment = new Comment(LocalDateTime.now(), Input.string("Type your comment:\n"), currentUser);
        postToReply.addComment(comment);
    }
    //endregion

    //region Follow User
    private static void followUser(){
        for (User u : users){
            System.out.println(u.getName());
        }

        String userToFollow = Input.string("Write the name of the user to follow:\n");
        for (User u : users){
            if (u.getName().equals(userToFollow)){
                currentUser.addFollowing(u);
            }
        }
    }
    //endregion

    //region Unfollow User
    private static void unfollowUser(){
        for (User u : currentUser.getFollowing()){
            System.out.println(u.getName());
        }

        String stringUserToUnfollow = Input.string("Write the name of the user to unfollow:\n");
        User userToUnfollow = null;
        for (User u : currentUser.getFollowing()){
            if (u.getName().equals(stringUserToUnfollow)){
                userToUnfollow = u;
            }
        }

        currentUser.removeFollowing(userToUnfollow);
    }
    //endregion

    //region List Posts
    private static void listPostsFromUser() {
        for (Post p : currentUser.getPosts()){
            System.out.println(p);
            System.out.println("----------");
        }
    }
    //endregion

    //region List Comments
    private static void listCommentsFromUser(){
        List<Comment> commentsFromUser = new ArrayList<>();

        for (User u : users){
            for (Post p : u.getPosts()){
                for (Comment c : p.getComments()) {
                    if (c.getOwner().getName().equals(currentUser.getName())){
                        commentsFromUser.add(c);
                    }
                }
            }
        }

        System.out.println("----------------------------------------");
        System.out.println("Comments from user: " + currentUser.getName());
        System.out.println("----------------------------------------");
        for (Comment c : commentsFromUser){
            System.out.println(c.toString());
        }
    }
    //endregion

    //region Delete post by user
    private static void deletePostByUser(){
        currentUser.removePosts();
    }
    //endregion

    //region Delete comments by user
    private static void deleteCommentByUser(){
        for (User u : users) {
            for (Post p : u.getPosts()){
                p.getComments().removeIf(c -> c.getOwner().getName().equals(currentUser.getName()));
            }
        }
    }
    //endregion

    //endregion

}
