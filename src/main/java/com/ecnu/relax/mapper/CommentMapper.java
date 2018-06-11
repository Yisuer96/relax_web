package com.ecnu.relax.mapper;

import com.ecnu.relax.model.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CommentMapper {
    @Delete({
        "delete from comment",
        "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer commentId);

    @Insert({
        "insert into comment (comment_id, order_id, ",
        "comment_time, content, ",
        "rating)",
        "values (#{commentId,jdbcType=INTEGER}, #{orderId,jdbcType=INTEGER}, ",
        "#{commentTime,jdbcType=TIMESTAMP}, #{content,jdbcType=VARCHAR}, ",
        "#{rating,jdbcType=DOUBLE})"
    })
    int insert(Comment record);

    int insertSelective(Comment record);

    @Select({
        "select",
        "comment_id, order_id, comment_time, content, rating",
        "from comment",
        "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    @Update({
        "update comment",
        "set order_id = #{orderId,jdbcType=INTEGER},",
          "comment_time = #{commentTime,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=VARCHAR},",
          "rating = #{rating,jdbcType=DOUBLE}",
        "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Comment record);

    @Select({
        "select *",
        "from `comment`",
        "where order_id in(",
            "select order_id",
            "from `order`",
            "where specialist_id = #{specialistId,jdbcType=INTEGER})"
    })
    List<Comment> selectCommentBySpecialistId(Integer specialistId);

    @Select({
            "select *",
            "from `comment`",
            "where order_id in(",
            "select order_id",
            "from `order`",
            "where patient_id = #{patientId,jdbcType=INTEGER})"
    })
    List<Comment> selectCommentByUserId(Integer patientId);
}