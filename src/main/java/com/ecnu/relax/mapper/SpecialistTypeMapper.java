package com.ecnu.relax.mapper;

import com.ecnu.relax.model.SpecialistTypeKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SpecialistTypeMapper {
    @Delete({
        "delete from specialist_type",
        "where specialist_id = #{specialistId,jdbcType=INTEGER}",
          "and type_id = #{typeId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(SpecialistTypeKey key);

    @Insert({
        "insert into specialist_type (specialist_id, type_id)",
        "values (#{specialistId,jdbcType=INTEGER}, #{typeId,jdbcType=INTEGER})"
    })
    int insert(SpecialistTypeKey record);

    int insertSelective(SpecialistTypeKey record);

    @Select({
        "select type_id",
        "from specialist_type",
        "where specialist_id=#{specialistId,jdbcType=INTEGER}"
    })
    List<Integer> selectBySpecialistId(Integer specialistId);
}