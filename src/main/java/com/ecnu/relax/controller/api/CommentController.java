package com.ecnu.relax.controller.api;

import com.ecnu.relax.dto.CommentDto;
import com.ecnu.relax.service.ICommentService;
import com.ecnu.relax.service.ISpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    ICommentService commentService;

    @Autowired
    ISpecialistService specialistService;

    @RequestMapping(value="/getCommentsByUserIdByIdentity", method = RequestMethod.GET)
    public List<CommentDto> getCommentsByUserIdByIdentity(@RequestParam("userId")Integer userId, @RequestParam("identity")Integer identity){
        List<CommentDto> commentDtos = new ArrayList<>();
        switch (identity){
            case 0:
                commentDtos = commentService.getCommentsByUserIdByIdentity(userId);
                break;
            case 1:
                commentDtos = specialistService.getUserCommentBySpecialistId(userId);
                break;
            default:
                break;
        }
        return commentDtos;
    }
}
