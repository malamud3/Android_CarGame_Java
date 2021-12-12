package com.example.project_2.Objects.DataManagement;

public class User {

    private String userName ;
    private String userPassword ;
    private String points;


public User(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean equals(User user){

    if(this.userName.equals(user.getUserName()))// same name
        if(this.userPassword.equals(user.getUserPassword()))
            return true;
        return false;

    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }


}
