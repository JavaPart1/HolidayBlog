package be.vdab.app;

import be.vdab.dao.UserDao;
import be.vdab.dao.UserDaoImpl;

import java.sql.SQLException;
import java.util.Scanner;

import static be.vdab.dao.JDBCPass.*;

public class BlogApp {
    public static void main(String[] args) {
        // Login

        // Menu : create post or create comment
    }

    private static boolean logInUser() throws SQLException {
        System.out.println("User log in ...");
        System.out.println(" ");
        boolean quitApp = false;
        Scanner input = new Scanner(System.in);
        UserDao userDao = new UserDaoImpl(JDBCURL,JDBCUSER,PASSW);
        String uName;
        String passW;

        boolean userNameExist = false;
        do {
            System.out.println("Username ? or quit to stop ");
            uName = input.nextLine();

            if (uName == "quit"){
                // quit app
                quitApp = true;
            } else {
                quitApp = false;
                if (userDao.detUserExist(uName) == 0){
                    System.out.println("Username " + uName + " does not exist ! Try another one or type 'quit' !");
                    userNameExist = false;
                } else {
                    userNameExist = true;
                }
            }
        } while ((!userNameExist) && (!quitApp));


        return true;
    }
}
