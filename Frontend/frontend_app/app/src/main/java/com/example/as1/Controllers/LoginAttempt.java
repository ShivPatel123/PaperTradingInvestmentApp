package com.example.as1.Controllers;

/**
 * Object Class to authenticate login
 * Mirrors the Login Attempt class on the backend
 */
public class LoginAttempt {

    protected String username;
    protected String password;

    /**
     * Constructor for a new instance of a LoginAttempt
     * @param Username
     * @param Password
     */
    public LoginAttempt(String Username, String Password){
        this.username = Username;
        this.password = Password;
    }

    /**
     * Getter for the login username
     * @return Username string used to login
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Getter for the login password
     * @return Password string used to login
     */
    public String getPassword(){
        return this.password;
    }

}
