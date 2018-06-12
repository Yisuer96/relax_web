package com.ecnu.relax.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ReplyDto {
    private Integer postReplyId;

    private Integer userId;

    private Integer postId;

    private Integer reReplyId;

    private String replyTime;

    private String content;

    private Integer replyNum;

    private String userName;

}
