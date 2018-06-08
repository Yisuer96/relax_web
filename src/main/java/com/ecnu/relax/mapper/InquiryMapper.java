package com.ecnu.relax.mapper;

import com.ecnu.relax.model.Inquiry;
import com.ecnu.relax.model.InquiryKey;
import org.apache.ibatis.annotations.*;

public interface InquiryMapper {
    @Delete({
        "delete from inquiry",
        "where sender_id = #{senderId,jdbcType=INTEGER}",
          "and receiver_id = #{receiverId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(InquiryKey key);

    @Insert({
        "insert into inquiry(sender_id, receiver_id, ",
        "message, send_time)",
        "values (#{sendId,jdbcType=INTEGER}, #{receiverId,jdbcType=INTEGER}, ",
        "#{message,jdbcType=VARCHAR}, #{sendTime,jdbcType=TIMESTAMP})"
    })
    int insert(Inquiry record);

    int insertSelective(Inquiry record);

    @Select({
        "select",
        "sender_id, receiver_id, message, send_time",
        "from inquiry",
        "where sender_id = #{senderId,jdbcType=INTEGER}",
          "and receiver_id = #{receiverId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Inquiry selectByPrimaryKey(InquiryKey key);

    //int updateByPrimaryKeySelective(Inquiry record);

    @Update({
        "update inquiry",
        "set message = #{message,jdbcType=VARCHAR}, send_time = #{sendTime,jdbcType=TIMESTAMP}",
        "where sender_id = #{senderId,jdbcType=INTEGER}",
          "and receiver_id = #{receiverId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Inquiry record);
}