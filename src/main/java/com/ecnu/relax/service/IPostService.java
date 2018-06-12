package com.ecnu.relax.service;

import com.ecnu.relax.dto.PostDto;
import com.ecnu.relax.dto.ReplyDto;
import com.ecnu.relax.model.Post;

import java.util.Date;
import java.util.List;

public interface IPostService extends BaseService {
    List<PostDto> getPostList();

    PostDto getPostBeanByPostId(int postId);

    ReplyDto getReplyBeanByReplyId(int replyId);

    List<ReplyDto> getReplyListById(int id, int isPost);

    Integer publishPost(Integer userId, String title, String content);

    Integer sendReply(Integer userId, String content, Integer toId, Integer isToPost);
}
