package be.vdab.dao;

import be.vdab.entity.Post;
import be.vdab.entity.User;

import java.sql.*;
import java.util.ArrayList;

public class PostDaoImpl implements PostDao{
    private String url;
    private String user;
    private String password;

    public PostDaoImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void createPost(Post nwPost) throws SQLException {
        String insert = "INSERT INTO post (idpost, title, text, creationdate, authorid) " +
                " VALUES (?, ?, ?, ?, ?)";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(insert)
        ) {
            stmt.setInt(1, nwPost.getId());
            stmt.setString(2, nwPost.getTitle());
            stmt.setString(3, nwPost.getText());
            stmt.setString(4, nwPost.getDate_creation());
            stmt.setInt(5, nwPost.getAuthor());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException(throwables);
        }
    }

    @Override
    public void updatePost(Post corPost) throws SQLException {

    }

    @Override
    public void deletePost(Post delPost) throws SQLException {

    }

    @Override
    public ArrayList<Post> getAllPosts() throws SQLException {
        String query = "SELECT * FROM post ";
        ArrayList<Post> arrayPost = new ArrayList<Post>();

        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                Post rsPost;
                while (rs.next()) {
                    rsPost = new Post();
                    rsPost.setId(rs.getInt("idpost"));
                    rsPost.setTitle(rs.getString("title"));
                    rsPost.setText(rs.getString("text"));
                    rsPost.setDate_creation(rs.getString("creationdate"));
                    arrayPost.add(rsPost);
                }
                return arrayPost;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException("Rec not found");
        }
    }

    @Override
    public ArrayList<Post> getPostForAuthor(User author) throws SQLException {
        String query = "SELECT * FROM post WHERE authorid=?";
        ArrayList<Post> arrayPost = new ArrayList<>();

        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            stmt.setInt(1, author.getId());
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                Post rsPost = new Post();
                while (rs.next()) {
                    rsPost.setId(rs.getInt("idpost"));
                    rsPost.setTitle(rs.getString("title"));
                    rsPost.setText(rs.getString("text"));
                    rsPost.setDate_creation(rs.getString("creationdate"));
                    rsPost.setAuthor(author);
                    arrayPost.add(rsPost);
                }
                return arrayPost;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException("Recs not found");
        }
    }

    public int detNbrPosts() throws SQLException {
        String query = "SELECT COUNT(*) FROM post ";
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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
