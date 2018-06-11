package com.ecnu.relax.service.impl;

import com.ecnu.relax.dto.CommentDto;
import com.ecnu.relax.mapper.CommentMapper;
import com.ecnu.relax.mapper.OrderMapper;
import com.ecnu.relax.mapper.UserMapper;
import com.ecnu.relax.model.Comment;
import com.ecnu.relax.service.ICommentService;
import com.ecnu.relax.service.ISpecialistService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl extends BaseServiceImpl implements ICommentService {
    @Autowired
    CommentMapper commentMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<CommentDto> getCommentsByUserIdByIdentity(int userId) {
        List<Comment> comments = commentMapper.selectCommentByUserId(userId);
        List<CommentDto> commentDtos = comments.stream().map(this::parse).collect(Collectors.toList());
        return commentDtos;
    }

    public CommentDto parse(Comment comment) {
        CommentDto commentDto = new CommentDto();
        try {
            BeanUtils.copyProperties(commentDto, comment);
            int orderId = comment.getOrderId();
            int userId = orderMapper.selectByPrimaryKey(orderId).getPatientId();
            String userName = userMapper.selectByPrimaryKey(userId).getNickname();
            commentDto.setCommenterName(userName);
        } catch (NullPointerException np) {
            System.out.println("Comments don't exist.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commentDto;
    }
}
