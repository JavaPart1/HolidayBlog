package be.vdab.app;

import be.vdab.dao.UserDao;
import be.vdab.dao.UserDaoImpl;
import be.vdab.entity.User;

import java.sql.SQLException;
import java.util.Scanner;

import static be.vdab.dao.JDBCPass.*;

public class UserAdminApp {
    public static void main(String[] args) {
        CreateUser();

    }

    public static void CreateUser(){
        // Initialisations
        System.out.println("Create user...");
        Scanner input = new Scanner(System.in);
        UserDao userDao = new UserDaoImpl(JDBCURL,JDBCUSER,PASSW);
        int nwId = 0;
        String uName;
        String passW;

        try {
            // Determine number of users for a new userId
            nwId = userDao.detNbrUsers() + 1;

            // Input username ; can not exist
            boolean userNameExist = false;
            do {
                System.out.println("Username ? ");
                uName = input.nextLine();

                if (userDao.detUserExist(uName) > 0){
                    System.out.println("Username " + uName + " already exist ! Try another one !");
                    userNameExist = true;
                } else {
                    userNameExist = false;
                }
            } while (userNameExist);

            // Input password
            System.out.println("Password ? ");
            passW = input.nextLine();

            // Create user object
            User user = new User(nwId, uName, passW);

            // Create user in DB
            userDao.createUser(user);
            System.out.println(" ");
            System.out.println("User "+ uName + " is created !");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
