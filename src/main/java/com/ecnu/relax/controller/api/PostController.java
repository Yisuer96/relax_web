package com.ecnu.relax.controller.api;

import com.ecnu.relax.dto.PostDto;
import com.ecnu.relax.dto.ReplyDto;
import com.ecnu.relax.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController extends APIBaseController{
    @Autowired
    private IPostService postService;

    @RequestMapping(value="/getPostList", method = RequestMethod.GET)
    public List<PostDto> getPostList(){
        List<PostDto> postDtoList = new ArrayList<>();
        postDtoList = postService.getPostList();
        return postDtoList;
    }

    @RequestMapping(value="/getPostBeanByPostId", method = RequestMethod.GET)
    public PostDto getPostBeanByPostId(@RequestParam("postId")Integer postId){
        PostDto postDto = postService.getPostBeanByPostId(postId);
        return postDto;
    }

    @RequestMapping(value="/getReplyListById", method = RequestMethod.GET)
    public List<ReplyDto> getReplyListById(@RequestParam("id")Integer id, @RequestParam("isPost")Integer isPost){
        List<ReplyDto> replyDtoList = new ArrayList<>();
        replyDtoList = postService.getReplyListById(id, isPost);
        return replyDtoList;
    }


    @RequestMapping(value="/getReplyBeanByReplyId", method = RequestMethod.GET)
    public ReplyDto getReplyBeanByReplyId(@RequestParam("replyId")Integer replyId){
        ReplyDto replyDto = postService.getReplyBeanByReplyId(replyId);
        return replyDto;
    }

    @RequestMapping(value="/publishPost", method = RequestMethod.GET)
    public Integer publishPost(@RequestParam("userId")Integer userId, @RequestParam("title")String title, @RequestParam("content")String content){
        Integer postId = postService.publishPost(userId, title, content);
        return postId;
    }

    @RequestMapping(value="/sendReply", method = RequestMethod.GET)
    public Integer sendReply(@RequestParam("userId")Integer userId, @RequestParam("toId")Integer toId,@RequestParam("isToPost")Integer isToPost, @RequestParam("content")String content){
        Integer postReplyId = postService.sendReply(userId, content, toId, isToPost);
        return postReplyId;
    }
}
