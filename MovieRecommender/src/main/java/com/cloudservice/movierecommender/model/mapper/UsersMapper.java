package com.cloudservice.movierecommender.model.mapper;

import com.cloudservice.movierecommender.model.bean.Users;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "UsersMapper")
public interface UsersMapper {
    @Delete({
        "delete from users",
        "where idUser = #{idUser,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer idUser);

    @Insert({
        "insert into users (idUser, `name`, ",
        "`password`, roleId)",
        "values (#{idUser,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{roleId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="idUser", before=false, resultType=Integer.class)
    int insert(Users record);

    @Select({
        "select",
        "idUser, `name`, `password`, roleId",
        "from users",
        "where idUser = #{idUser,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="idUser", property="idUser", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="roleId", property="roleId", jdbcType=JdbcType.INTEGER)
    })
    Users selectByPrimaryKey(Integer idUser);

    @Select({
        "select",
        "idUser, `name`, `password`, roleId",
        "from users"
    })
    @Results({
        @Result(column="idUser", property="idUser", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="roleId", property="roleId", jdbcType=JdbcType.INTEGER)
    })
    List<Users> selectAll();

    @Update({
        "update users",
        "set `name` = #{name,jdbcType=VARCHAR},",
          "`password` = #{password,jdbcType=VARCHAR},",
          "roleId = #{roleId,jdbcType=INTEGER}",
        "where idUser = #{idUser,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Users record);
}