package com.model;

public class PostDTO {
    public String title;
    public String body;
    public int userId;

    public PostDTO(int userId, String title, String body) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
}
