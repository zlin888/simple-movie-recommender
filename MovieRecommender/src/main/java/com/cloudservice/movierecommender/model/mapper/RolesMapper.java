package com.cloudservice.movierecommender.model.mapper;

import com.cloudservice.movierecommender.model.bean.Roles;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "RolesMapper")
public interface RolesMapper {
    @Delete({
        "delete from roles",
        "where idRole = #{idRole,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer idRole);

    @Insert({
        "insert into roles (idRole, `role`)",
        "values (#{idRole,jdbcType=INTEGER}, #{role,jdbcType=VARCHAR})"
    })
    int insert(Roles record);

    @Select({
        "select",
        "idRole, `role`",
        "from roles",
        "where idRole = #{idRole,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="idRole", property="idRole", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR)
    })
    Roles selectByPrimaryKey(Integer idRole);

    @Select({
        "select",
        "idRole, `role`",
        "from roles"
    })
    @Results({
        @Result(column="idRole", property="idRole", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR)
    })
    List<Roles> selectAll();

    @Update({
        "update roles",
        "set `role` = #{role,jdbcType=VARCHAR}",
        "where idRole = #{idRole,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Roles record);
}