package be.vdab.app;

import be.vdab.dao.*;
import be.vdab.entity.Post;
import be.vdab.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static be.vdab.dao.JDBCPass.*;

public class BlogApp {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        // Login
        String uName = "";
        String passW = "";
        try {
            uName = logInUser(uName,passW);
            if (!uName.isBlank()){
                // Menu : create post or create comment
                System.out.println("Menu:");
                System.out.println("Choose '1' to post a message");
                System.out.println("Choose '2' to comment on a post");
                int choice = input.nextInt();

                switch (choice){
                    case 1: createPost(uName);
                    break;
                    case 2: createComment(uName);
                    break;
                    default:
                        System.out.println("wrong choice, stopping app ...");
                }

            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getErrorCode());
            throwables.printStackTrace();
        }

    }

    private static void createPost(String uName) throws SQLException {
        Scanner input = new Scanner(System.in);
        // Gather user info
        UserDao userDao = new UserDaoImpl(JDBCURL,JDBCUSER,PASSW);
        User postWriter = userDao.getUserByName(uName);
        PostDao postDao = new PostDaoImpl(JDBCURL,JDBCUSER,PASSW);

        // Initialisations new post
        int nwPostId = 0;
        String nwTitle;
        String nwText;

        try {
            // Determine nbr of posts for new postid
            nwPostId = postDao.detNbrPosts() + 1;

            // Input title & text
            System.out.println("Title? ");
            nwTitle = input.nextLine();
            System.out.println("Text?");
            nwText = input.nextLine();

            // Create post object
            Post nwPost = new Post(nwPostId,nwTitle,nwText,postWriter);

            // Save post in DB
            postDao.createPost(nwPost);
            System.out.println(" ");
            System.out.println("Post " + nwTitle + " is created !");


        } catch (SQLException throwables) {
            System.out.println("SQL error: " + throwables.getErrorCode());
            throwables.printStackTrace();
        }

    }

    private static void createComment(String uName) throws SQLException {
        Scanner input = new Scanner(System.in);
        // Gather user info
        UserDao userDao = new UserDaoImpl(JDBCURL,JDBCUSER,PASSW);
        User postWriter = userDao.getUserByName(uName);

        // Gather post info
        PostDao postDao = new PostDaoImpl(JDBCURL,JDBCUSER,PASSW);
        ArrayList<Post> listPosts = postDao.getAllPosts();

        System.out.println("Titels vn posts: ");
        for (int i = 0; i < listPosts.size() ; i++) {
            System.out.println(i + " " + listPosts.get(i).getTitle());
            System.out.print(" " + listPosts.get(i).getText());

        }
        System.out.println(" ");
        System.out.println("Choose a postnumber to give comment on :");
        int t = input.nextInt();

        // Initialisations new post
        int nwCommentId = 0;
        String nwCommentText = "";
        int authorId = postWriter.getId();
        int postId = listPosts.get(t).getId();
        //CommentDao commentDao =

        try {
            // Determine nbr of comments for new commentid
            nwCommentId = postDao.detNbrPosts() + 1;

            // Input title & text
            System.out.println("Text?");
            //nwText = input.nextLine();

            // Create post object
            //Post nwPost = new Post(nwPostId,nwTitle,nwText,postWriter);

            // Save post in DB
            //postDao.createPost(nwPost);
            System.out.println(" ");
            //System.out.println("Post " + nwTitle + " is created !");


        } catch (SQLException throwables) {
            System.out.println("SQL error: " + throwables.getErrorCode());
            throwables.printStackTrace();
        }

    }

    private static String logInUser(String uName,String passW) throws SQLException {
        System.out.println("User log in ...");
        System.out.println(" ");
        boolean quitApp = false;
        Scanner input = new Scanner(System.in);
        UserDao userDao = new UserDaoImpl(JDBCURL,JDBCUSER,PASSW);

        boolean userPassCorrect = false;
        do {
            System.out.println("Username ? or quit to stop ");
            uName = input.nextLine();
            System.out.println("Password ? or quit to stop ");
            passW = input.nextLine();

            if ((uName.equals("quit")) || (passW.equals("quit"))){
                // quit app
                quitApp = true;
            } else {
                quitApp = false;
                if (userDao.detUserPass(uName,passW) == 0){
                    System.out.println("Username or password incorrect ! Try another one or type 'quit' !");
                    userPassCorrect = false;
                } else {
                    userPassCorrect = true;
                }
            }
        } while ((!userPassCorrect) && (!quitApp));

        if (quitApp){
            System.out.println("Stopping app ...");
            return " ";
        } else {
            System.out.println("Login credentials correct !");
            return uName;
        }
    }
}
