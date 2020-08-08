package be.vdab.dao;

import be.vdab.entity.Comment;
import be.vdab.entity.Post;
import be.vdab.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CommentDao {
    public void createComment(Comment nwComment) throws SQLException;
    public void updateComment(Comment corComment) throws SQLException;
    public void deleteComment(Comment delComment) throws SQLException;
    public int detNbrOfComments() throws SQLException;
    public ArrayList<Comment> getCommentForAuthor(User author) throws SQLException;
    public ArrayList<Comment> getCommentForPost(Post post) throws SQLException;
}
