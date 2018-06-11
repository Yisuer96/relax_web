package com.ecnu.relax.service;

import com.ecnu.relax.dto.CommentDto;

import java.util.List;

public interface ICommentService extends BaseService {
    List<CommentDto> getCommentsByUserIdByIdentity(int userId);
}
