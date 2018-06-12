package com.ecnu.relax.model;

import java.util.Date;

public class PostReply {
    private Integer postReplyId;

    private Integer userId;

    private Integer postId;

    private Integer reReplyId;

    private Date replyTime;

    private String content;

    public PostReply() {
    }

    public PostReply(Integer userId, Integer postId, Integer reReplyId, Date replyTime, String content) {
        this.userId = userId;
        this.postId = postId;
        this.reReplyId = reReplyId;
        this.replyTime = replyTime;
        this.content = content;
    }

    public Integer getPostReplyId() {
        return postReplyId;
    }

    public void setPostReplyId(Integer postReplyId) {
        this.postReplyId = postReplyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getReReplyId() {
        return reReplyId;
    }

    public void setReReplyId(Integer reReplyId) {
        this.reReplyId = reReplyId;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}