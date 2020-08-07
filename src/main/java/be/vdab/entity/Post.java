package be.vdab.entity;

import java.util.Date;

public class Post {
    private int id;
    private String title;
    private String text;
    private String date_creation;
    private User author;

    public Post() {
    }

    public Post(int nwId, String title, String text, User author) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.date_creation = new Date().toString();
        this.id = nwId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthor() {
        return author.getId();
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
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

    public String getDate_creation() {
        return date_creation.toString();
    }

}
