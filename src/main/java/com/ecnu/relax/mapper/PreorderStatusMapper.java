package com.ecnu.relax.mapper;

import com.ecnu.relax.model.PreorderStatus;
import com.ecnu.relax.model.PreorderStatusKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PreorderStatusMapper {
    @Delete({
        "delete from preorder_status",
        "where timeslot_id = #{timeslotId,jdbcType=INTEGER}",
          "and specialist_id = #{specialistId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(PreorderStatusKey key);

    @Insert({
        "insert into preorder_status (timeslot_id, specialist_id, ",
        "is_ordered, is_free, day)",
        "values (#{timeslotId,jdbcType=INTEGER}, #{specialistId,jdbcType=INTEGER}, ",
        "#{isOdered,jdbcType=INTEGER}, #{isFree,jdbcType=INTEGER}, #{day,jdbcType=INTEGER})"
    })
    int insert(PreorderStatus record);

    int insertSelective(PreorderStatus record);

    @Select({
        "select",
        "timeslot_id, specialist_id, is_ordered, is_free, day",
        "from preorder_status",
        "where timeslot_id = #{timeslotId,jdbcType=INTEGER}",
          "and specialist_id = #{specialistId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    PreorderStatus selectByPrimaryKey(PreorderStatusKey key);

    int updateByPrimaryKeySelective(PreorderStatus record);

    @Update({
        "update preorder_status",
        "set is_ordered = #{isOdered,jdbcType=INTEGER}, is_free = #{isFree,jdbcType=INTEGER}, day = #{day,jdbcType=INTEGER}",
        "where timeslot_id = #{timeslotId,jdbcType=INTEGER}",
          "and specialist_id = #{specialistId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PreorderStatus record);
}