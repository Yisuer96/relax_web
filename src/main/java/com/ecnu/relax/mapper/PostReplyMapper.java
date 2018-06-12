package com.ecnu.relax.mapper;

import com.ecnu.relax.model.PostReply;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PostReplyMapper {
    @Delete({
        "delete from post_reply",
        "where post_reply_id = #{postReplyId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer postReplyId);

    @Insert({
        "insert into post_reply (post_reply_id, user_id, ",
        "post_id, re_reply_id, ",
        "reply_time, content)",
        "values (#{postReplyId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{postId,jdbcType=INTEGER}, #{reReplyId,jdbcType=INTEGER}, ",
        "#{replyTime,jdbcType=TIMESTAMP}, #{content,jdbcType=VARCHAR})"
    })
    int insert(PostReply record);

    int insertSelective(PostReply record);

    @Select({
        "select",
        "post_reply_id, user_id, post_id, re_reply_id, reply_time, content",
        "from post_reply",
        "where post_reply_id = #{postReplyId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    PostReply selectByPrimaryKey(Integer postReplyId);

    @Select({
            "select count(post_reply_id)",
            "from post_reply",
            "where re_reply_id = #{replyId,jdbcType=INTEGER}"
    })
    Integer getReplyNumByReplyId(Integer replyId);

    @Select({
            "select *",
            "from post_reply",
            "where post_id = #{postId,jdbcType=INTEGER}",
            "order by reply_time"
    })
    List<PostReply> getReplyListByPostId(Integer postId);

    @Select({
            "select *",
            "from post_reply",
            "where re_reply_id = #{replyId,jdbcType=INTEGER}",
            "order by reply_time"
    })
    List<PostReply> getReplyListByReplyId(Integer replyId);

    int updateByPrimaryKeySelective(PostReply record);

    @Update({
        "update post_reply",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "post_id = #{postId,jdbcType=INTEGER},",
          "re_reply_id = #{reReplyId,jdbcType=INTEGER},",
          "reply_time = #{replyTime,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=VARCHAR}",
        "where post_reply_id = #{postReplyId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PostReply record);
}