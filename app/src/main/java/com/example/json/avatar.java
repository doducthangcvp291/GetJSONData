package com.example.json;

public class avatar {
    private String linkavatar;
    private String name;
    private String email;

    public String getLinkavatar() {
        return linkavatar;
    }

    public avatar(String linkavatar, String name, String email) {
        this.linkavatar = linkavatar;
        this.name = name;
        this.email = email;
    }

    public avatar setLinkavatar(String linkavatar) {
        this.linkavatar = linkavatar;
        return this;
    }

    public String getName() {
        return name;
    }

    public avatar setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public avatar setEmail(String email) {
        this.email = email;
        return this;
    }
}