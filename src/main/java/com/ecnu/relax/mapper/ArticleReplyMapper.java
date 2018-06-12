package com.ecnu.relax.mapper;

import com.ecnu.relax.model.ArticleReply;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ArticleReplyMapper {
    @Delete({
        "delete from article_reply",
        "where article_reply_id = #{articleReplyId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer articleReplyId);

    @Insert({
        "insert into article_reply (article_reply_id, specialist_id, ",
        "article_id, re_reply_id, ",
        "reply_time, content)",
        "values (#{articleReplyId,jdbcType=INTEGER}, #{specialistId,jdbcType=INTEGER}, ",
        "#{articleId,jdbcType=INTEGER}, #{reReplyId,jdbcType=INTEGER}, ",
        "#{replyTime,jdbcType=TIMESTAMP}, #{content,jdbcType=VARCHAR})"
    })
    int insert(ArticleReply record);

    int insertSelective(ArticleReply record);

    @Select({
        "select",
        "article_reply_id, specialist_id, article_id, re_reply_id, reply_time, content",
        "from article_reply",
        "where article_reply_id = #{articleReplyId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    ArticleReply selectByPrimaryKey(Integer articleReplyId);

    int updateByPrimaryKeySelective(ArticleReply record);

    @Update({
        "update article_reply",
        "set specialist_id = #{specialistId,jdbcType=INTEGER},",
          "article_id = #{articleId,jdbcType=INTEGER},",
          "re_reply_id = #{reReplyId,jdbcType=INTEGER},",
          "reply_time = #{replyTime,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=VARCHAR}",
        "where article_reply_id = #{articleReplyId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ArticleReply record);
}