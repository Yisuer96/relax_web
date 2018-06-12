package com.ecnu.relax.model;

import java.util.Date;

public class Post {
    private Integer postId;

    private Integer userId;

    private Date publishTime;

    private String title;

    private String content;

    public Post() {
    }

    public Post(Integer userId, Date publishTime, String title, String content) {
        this.userId = userId;
        this.publishTime = publishTime;
        this.title = title;
        this.content = content;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}