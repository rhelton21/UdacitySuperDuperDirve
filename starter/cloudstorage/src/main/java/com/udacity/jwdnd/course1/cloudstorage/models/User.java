package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.Data;

@Data
public class User {
    private Integer userid;
    private String username;
    private String salt;
    private String password;
    private String firstname;
    private String lastname;

    public User(Integer userid, String username, String salt, String password, String firstname, String lastname) {
        this.userid = userid;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

}