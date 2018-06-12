package com.ecnu.relax.mapper;

import com.ecnu.relax.model.Post;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PostMapper {
    @Delete({
        "delete from post",
        "where post_id = #{postId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer postId);

    @Insert({
        "insert into post (post_id, user_id, ",
        "publish_time, title, ",
        "content)",
        "values (#{postId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{publishTime,jdbcType=TIMESTAMP}, #{title,jdbcType=VARCHAR}, ",
        "#{content,jdbcType=VARCHAR})"
    })
    int insert(Post record);

    int insertSelective(Post record);

    @Select({
        "select",
        "post_id, user_id, publish_time, title, content",
        "from post",
        "where post_id = #{postId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Post selectByPrimaryKey(Integer postId);

    @Select({
            "select *",
            "from post",
            "order by publish_time desc"
    })
    List<Post> selectAll();

    int updateByPrimaryKeySelective(Post record);

    @Update({
        "update post",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "publish_time = #{publishTime,jdbcType=TIMESTAMP},",
          "title = #{title,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR}",
        "where post_id = #{postId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Post record);
}