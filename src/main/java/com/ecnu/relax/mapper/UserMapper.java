package com.ecnu.relax.mapper;

import com.ecnu.relax.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Delete({
        "delete from user",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userId);

    @Insert({
        "insert into user (user_id, nickname, ",
        "phone, password, ",
        "real_name, identity, ",
        "remainder, portrait, ",
        "relax_degree)",
        "values (#{userId,jdbcType=INTEGER}, #{nickname,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{realName,jdbcType=VARCHAR}, #{identity,jdbcType=INTEGER}, ",
        "#{remainder,jdbcType=DOUBLE}, #{portrait,jdbcType=VARCHAR}, ",
        "#{relaxDegree,jdbcType=DOUBLE})"
    })
    int insert(User record);

    int insertSelective(User record);

    @Select({
        "select",
        "user_id, nickname, phone, password, real_name, identity, remainder, portrait, ",
        "relax_degree",
        "from user",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    @Update({
        "update user",
        "set nickname = #{nickname,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "real_name = #{realName,jdbcType=VARCHAR},",
          "identity = #{identity,jdbcType=INTEGER},",
          "remainder = #{remainder,jdbcType=DOUBLE},",
          "portrait = #{portrait,jdbcType=VARCHAR},",
          "relax_degree = #{relaxDegree,jdbcType=DOUBLE}",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);

    @Select({
            "select",
            "user_id, nickname, phone, password, real_name, identity, remainder, portrait, ",
            "relax_degree",
            "from user",
            "where phone = #{phone,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    User selectByPhone(String phone);

    @Update({
            "update user",
            "set identity = #{identity,jdbcType=INTEGER},",
            "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateIdentityByUserId(Integer identity,Integer userId);
}