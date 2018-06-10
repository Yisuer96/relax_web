package com.ecnu.relax.mapper;

import com.ecnu.relax.model.Inquiry;
import org.apache.ibatis.annotations.*;

public interface InquiryMapper {
    @Delete({
        "delete from inquiry",
        "where inquiry_id = #{inquiryId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer inquiryId);

    @Insert({
        "insert into inquiry(inquiry_id, sender_id, receiver_id, ",
        "message, send_time)",
        "values (#{inquiryId,jdbcType=INTEGER}, #{sendId,jdbcType=INTEGER}, #{receiverId,jdbcType=INTEGER}, ",
        "#{message,jdbcType=VARCHAR}, #{sendTime,jdbcType=TIMESTAMP})"
    })
    int insert(Inquiry record);

    int insertSelective(Inquiry record);

    @Select({
        "select",
        "inquiry_id, sender_id, receiver_id, message, send_time",
        "from inquiry",
        "where inquiry_id = #{inquiryId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Inquiry selectByPrimaryKey(Integer inquiryId);

    int updateByPrimaryKeySelective(Inquiry record);

    @Update({
        "update inquiry",
        "set sender_id = #{senderId,jdbcType=INTEGER}, receiver_id = #{receiverId,jdbcType=INTEGER},",
            "message = #{message,jdbcType=VARCHAR}, send_time = #{sendTime,jdbcType=TIMESTAMP}",
        "where inquiry_id = #{inquiryId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Inquiry record);
}