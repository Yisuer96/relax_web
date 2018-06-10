package com.ecnu.relax.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CommentDto {
    private Integer commentId;

    private Integer orderId;

    private Long commentTime;

    private String content;

    private Double rating;

    private String commenterName;
}
