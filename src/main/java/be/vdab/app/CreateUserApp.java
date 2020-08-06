package be.vdab.app;

import be.vdab.dao.UserDao;
import be.vdab.dao.UserDaoImpl;
import be.vdab.entity.User;

import java.sql.SQLException;
import java.util.Scanner;

import static be.vdab.dao.JDBCPass.*;

public class CreateUserApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // create user: username & password
        System.out.println("Create user...");
        System.out.println("Username ? ");
        String uName = input.nextLine();
        System.out.println("Password ? ");
        String passW = input.nextLine();
        User user = new User(uName,passW);

        //userdao
        UserDao userDao = new UserDaoImpl(JDBCURL,JDBCUSER,PASSW);
        try {
            userDao.createUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
