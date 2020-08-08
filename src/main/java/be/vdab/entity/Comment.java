package be.vdab.entity;

public class Comment {
    private int id;
    private String text;
    private User author;
    private Post post;

    public Comment(int nwId,String text, User author, Post post) {
        this.text = text;
        this.author = author;
        this.post = post;
        this.id = nwId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
