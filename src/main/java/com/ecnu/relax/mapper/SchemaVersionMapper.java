package com.ecnu.relax.mapper;

import com.ecnu.relax.model.SchemaVersion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SchemaVersionMapper {
    @Delete({
        "delete from schema_version",
        "where version = #{version,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String version);

    @Insert({
        "insert into schema_version (version, version_rank, ",
        "installed_rank, description, ",
        "type, script, checksum, ",
        "installed_by, installed_on, ",
        "execution_time, success)",
        "values (#{version,jdbcType=VARCHAR}, #{versionRank,jdbcType=INTEGER}, ",
        "#{installedRank,jdbcType=INTEGER}, #{description,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR}, #{script,jdbcType=VARCHAR}, #{checksum,jdbcType=INTEGER}, ",
        "#{installedBy,jdbcType=VARCHAR}, #{installedOn,jdbcType=TIMESTAMP}, ",
        "#{executionTime,jdbcType=INTEGER}, #{success,jdbcType=BIT})"
    })
    int insert(SchemaVersion record);

    int insertSelective(SchemaVersion record);

    @Select({
        "select",
        "version, version_rank, installed_rank, description, type, script, checksum, ",
        "installed_by, installed_on, execution_time, success",
        "from schema_version",
        "where version = #{version,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    SchemaVersion selectByPrimaryKey(String version);

    int updateByPrimaryKeySelective(SchemaVersion record);

    @Update({
        "update schema_version",
        "set version_rank = #{versionRank,jdbcType=INTEGER},",
          "installed_rank = #{installedRank,jdbcType=INTEGER},",
          "description = #{description,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "script = #{script,jdbcType=VARCHAR},",
          "checksum = #{checksum,jdbcType=INTEGER},",
          "installed_by = #{installedBy,jdbcType=VARCHAR},",
          "installed_on = #{installedOn,jdbcType=TIMESTAMP},",
          "execution_time = #{executionTime,jdbcType=INTEGER},",
          "success = #{success,jdbcType=BIT}",
        "where version = #{version,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SchemaVersion record);
}