package com.ecnu.relax.model;

import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class Comment {
    private Integer commentId;

    private Integer orderId;

    private Date commentTime;

    private String content;

    private Double rating;

    public Comment(Integer orderId,String content,Double rating)
    {
        this.orderId = orderId;
        commentTime = new Date();
        this.content = content;
        this.rating = rating;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}