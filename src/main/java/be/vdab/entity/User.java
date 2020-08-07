package be.vdab.entity;

public class User {
    private int id;
    private String userName;
    private String passWord;

    public User() {
    }

    public User(int nwId,String userName, String passWord) {
        this.id = nwId;
        this.userName = userName;
        this.passWord = passWord;
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
