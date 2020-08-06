package be.vdab.entity;

public class Comment {
    private int id;
    private String text;
    private User author;
    private Post post;

    public Comment(String text, User author, Post post) {
        this.text = text;
        this.author = author;
        this.post = post;
    }
}
