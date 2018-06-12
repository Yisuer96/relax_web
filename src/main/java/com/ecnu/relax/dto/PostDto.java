package com.ecnu.relax.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PostDto {
    private Integer postId;

    private Integer userId;

    private String title;

    private String content;

    private String userName;

    private String time;

}
