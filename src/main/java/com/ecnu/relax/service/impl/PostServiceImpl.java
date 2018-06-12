package com.ecnu.relax.service.impl;

import com.ecnu.relax.dto.PostDto;
import com.ecnu.relax.dto.ReplyDto;
import com.ecnu.relax.mapper.PostMapper;
import com.ecnu.relax.mapper.PostReplyMapper;
import com.ecnu.relax.mapper.UserMapper;
import com.ecnu.relax.model.Post;
import com.ecnu.relax.model.PostReply;
import com.ecnu.relax.model.User;
import com.ecnu.relax.service.IPostService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl extends BaseServiceImpl implements IPostService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    PostMapper postMapper;

    @Autowired
    PostReplyMapper postReplyMapper;

    @Override
    public List<PostDto> getPostList() {
        List<Post> posts = new ArrayList<>();
        List<PostDto> postDtos = new ArrayList<>();
        posts = postMapper.selectAll();
        postDtos = posts.stream().map(this::parse).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostDto getPostBeanByPostId(int postId) {
        Post post = postMapper.selectByPrimaryKey(postId);
        PostDto postDto = parse(post);
        return postDto;

    }

    @Override
    public ReplyDto getReplyBeanByReplyId(int replyId) {
        PostReply reply = postReplyMapper.selectByPrimaryKey(replyId);
        ReplyDto replyDto = parseReply(reply);
        return replyDto;

    }

    @Override
    public List<ReplyDto> getReplyListById(int id, int isPost) {
        List<PostReply> postReplys = new ArrayList<>();
        List<ReplyDto> replyDtos = new ArrayList<>();
        if(isPost==0){
            postReplys = postReplyMapper.getReplyListByPostId(id);
        }else {
            postReplys = postReplyMapper.getReplyListByReplyId(id);
        }
        replyDtos = postReplys.stream().map(this::parseReply).collect(Collectors.toList());
        return replyDtos;
    }


    private ReplyDto parseReply(PostReply postReply) {
        ReplyDto replyDto = new ReplyDto();
        try {
            BeanUtils.copyProperties(replyDto, postReply);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            replyDto.setReplyTime(sdf.format(postReply.getReplyTime()));
            Integer replyNum = postReplyMapper.getReplyNumByReplyId(postReply.getPostReplyId());
            replyDto.setReplyNum(replyNum);
            User user = userMapper.selectByPrimaryKey(postReply.getUserId());
            replyDto.setUserName(user.getNickname());

        } catch (NullPointerException np) {
            System.out.println("PostReply doesn't exist.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return replyDto;

    }

    private PostDto parse(Post post) {
        PostDto postDto = new PostDto();
        try {
            BeanUtils.copyProperties(postDto, post);
            User user = userMapper.selectByPrimaryKey(post.getUserId());
            postDto.setUserName(user.getNickname());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            postDto.setTime(sdf.format(post.getPublishTime()));

        } catch (NullPointerException np) {
            System.out.println("Post doesn't exist.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postDto;
    }

    @Override
    public Integer publishPost(Integer userId, String title, String content){
        Date date = new Date();
        Post post = new Post(userId,date,title,content);
        postMapper.insertSelective(post);
        return post.getPostId();
    }

    @Override
    public Integer sendReply(Integer userId, String content, Integer toId, Integer isToPost) {
        Date date = new Date();
        PostReply reply = new PostReply();
        if(isToPost==0) {
            reply = new PostReply(userId,toId,-1,date,content);
        }else {
            reply = new PostReply(userId,-1,toId,date,content);
        }
        postReplyMapper.insertSelective(reply);
        return reply.getPostReplyId();
    }
}
