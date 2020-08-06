package be.vdab.entity;

import java.util.Date;

public class Post {
    private int id;
    private String title;
    private String text;
    private Date date_creation;
    private User author;
    static int nbrOfPosts;

    public Post(String title, String text, User author) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.date_creation = new Date();
        this.id = getNbrOfPosts() + 1;

        setNbrOfPosts(getNbrOfPosts() + 1);
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public static int getNbrOfPosts() {
        return nbrOfPosts;
    }

    public static void setNbrOfPosts(int nbrOfPosts) {
        Post.nbrOfPosts = nbrOfPosts;
    }
}
