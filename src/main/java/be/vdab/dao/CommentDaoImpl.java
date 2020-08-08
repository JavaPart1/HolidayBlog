package be.vdab.dao;

import be.vdab.entity.Comment;
import be.vdab.entity.Post;
import be.vdab.entity.User;

import java.sql.*;
import java.util.ArrayList;

public class CommentDaoImpl implements CommentDao {
    private String url;
    private String user;
    private String password;

    public CommentDaoImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void createComment(Comment nwComment) throws SQLException {
        String insert = "INSERT INTO comment (idComment, text, authorid, postid) " +
                " VALUES (?, ?, ?, ?)";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(insert)
        ) {
            stmt.setInt(1, nwComment.getId());
            stmt.setString(2, nwComment.getText());
            stmt.setInt(3, nwComment.getAuthor().getId());
            stmt.setInt(4, nwComment.getPost().getId());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException(throwables);
        }

    }

    @Override
    public void updateComment(Comment corComment) throws SQLException {

    }

    @Override
    public void deleteComment(Comment delComment) throws SQLException {

    }

    @Override
    public int detNbrOfComments() throws SQLException {
        String query = "SELECT COUNT(*) FROM comment ";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("SQL error l2");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException("SQL error l1");
        }
    }

    @Override
    public ArrayList<Comment> getCommentForAuthor(User author) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Comment> getCommentForPost(Post post) throws SQLException {
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
