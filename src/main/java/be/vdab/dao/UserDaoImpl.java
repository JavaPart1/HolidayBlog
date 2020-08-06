package be.vdab.dao;

import be.vdab.entity.User;

import java.sql.*;

public class UserDaoImpl implements UserDao{
    private String url;
    private String user;
    private String password;

    public UserDaoImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void createUser(User nwUser) throws SQLException {
        String insert = "INSERT INTO user (iduser, username, password) " +
                " VALUES (?, ?, ?)";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(insert)
        ) {
            stmt.setInt(1, nwUser.getId());
            stmt.setString(2, nwUser.getUserName());
            stmt.setString(3, nwUser.getPassWord());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException(throwables);
        }

    }

    @Override
    public void updateUser(User corUser) throws SQLException {

    }

    @Override
    public void deleteUser(User delUser) throws SQLException {

    }

    @Override
    public User getUserByName(String srName) throws SQLException {
        String query = "SELECT * FROM user WHERE username=?";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            stmt.setString(1, srName);
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                if (rs.next()) {
                    User person = new User();
                    person.setUserName(rs.getString("username"));
                    person.setPassWord(rs.getString("password"));
                    return person;
                } else {
                    System.out.println("User not found with name : " + srName);
                    throw new SQLException("Record not found");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException("Rec not found");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
