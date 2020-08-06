package be.vdab.entity;

public class User {
    private int id;
    private String userName;
    private String passWord;
    static int nbrOfUsers;

    public User() {
    }

    public User(String userName, String passWord) {
        this.id = getNbrOfUsers() + 1;
        this.userName = userName;
        this.passWord = passWord;

        setNbrOfUsers(getNbrOfUsers() + 1);
    }

    public static int getNbrOfUsers() {
        return nbrOfUsers;
    }

    public static void setNbrOfUsers(int nbrOfUsers) {
        User.nbrOfUsers = nbrOfUsers;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }
}
