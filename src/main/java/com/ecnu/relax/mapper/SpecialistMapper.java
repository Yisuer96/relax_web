package com.ecnu.relax.mapper;

import com.ecnu.relax.model.Specialist;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SpecialistMapper {
    @Delete({
        "delete from specialist",
        "where specialist_id = #{specialistId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer specialistId);

    @Insert({
        "insert into specialist (specialist_id, rating, ",
        "qualification, employ_length, ",
        "introduction)",
        "values (#{specialistId,jdbcType=INTEGER}, #{rating,jdbcType=DOUBLE}, ",
        "#{qualification,jdbcType=VARCHAR}, #{employLength,jdbcType=INTEGER}, ",
        "#{introduction,jdbcType=VARCHAR})"
    })
    int insert(Specialist record);

    int insertSelective(Specialist record);

    @Select({
        "select",
        "specialist_id, rating, qualification, employ_length, introduction",
        "from specialist",
        "where specialist_id = #{specialistId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Specialist selectByPrimaryKey(Integer specialistId);

    int updateByPrimaryKeySelective(Specialist record);

    @Update({
        "update specialist",
        "set rating = #{rating,jdbcType=DOUBLE},",
          "qualification = #{qualification,jdbcType=VARCHAR},",
          "employ_length = #{employLength,jdbcType=INTEGER},",
          "introduction = #{introduction,jdbcType=VARCHAR}",
        "where specialist_id = #{specialistId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Specialist record);

    @Select({
            "select *",
            "from specialist",
            "where specialist_id in(",
                "select specialist_type.specialist_id",
                "from specialist_type",
                "where type_id = #{typeId,jdbcType=INTEGER})",
            "order by rating desc"
    })
    List<Specialist> getRatingDescSpecialistsByType(Integer typeId);

    @Select({
            "select *",
            "from specialist",
            "where specialist_id in(",
            "select specialist_type.specialist_id",
            "from specialist_type",
            "where type_id = #{typeId,jdbcType=INTEGER})",
            "order by order_num desc"
    })
    List<Specialist> getOrdersNumDescDescSpecialistsByType(Integer typeId);

    @Select({
            "select *",
            "from specialist",
            "where specialist_id in(",
            "select specialist_type.specialist_id",
            "from specialist_type",
            "where type_id = #{typeId,jdbcType=INTEGER})",
            "order by employ_length desc"
    })
    List<Specialist> getEmployLengthDescSpecialistsByType(Integer typeId);

    @Select({
            "select *",
            "from specialist",
            "order by rating desc"
    })
    List<Specialist> getRatingDescSpecialists();

    @Select({
            "select *",
            "from specialist",
            "order by order_num desc"
    })
    List<Specialist> getOrdersNumDescDescSpecialists();

    @Select({
            "select *",
            "from specialist",
            "order by employ_length desc"
    })
    List<Specialist> getEmployLengthDescSpecialists();
}