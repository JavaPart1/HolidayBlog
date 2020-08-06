package be.vdab.dao;

import be.vdab.entity.Post;
import be.vdab.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PostDao {
    public void createPost(Post nwPost) throws SQLException;
    public void updatePost(Post corPost) throws SQLException;
    public void deletePost(Post delPost) throws SQLException;
    public ArrayList<Post> getPostForAuthor(User author) throws SQLException;
}
