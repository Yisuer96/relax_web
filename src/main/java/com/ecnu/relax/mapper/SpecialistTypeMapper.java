package com.ecnu.relax.mapper;

import com.ecnu.relax.model.Order;
import com.ecnu.relax.model.SpecialistTypeKey;
import com.ecnu.relax.model.Type;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
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
            "select * ",
            "from `specialist_type`",
            "where specialist_id = #{specialistId} order by type_id"
    })
    @ResultMap("BaseResultMap")
    List<SpecialistTypeKey> getSpecialistTypes (Integer specialistId);

    @Select({
        "select type_id",
        "from specialist_type",
        "where specialist_id=#{specialistId,jdbcType=INTEGER}"
    })
    List<Integer> selectBySpecialistId(Integer specialistId);
}