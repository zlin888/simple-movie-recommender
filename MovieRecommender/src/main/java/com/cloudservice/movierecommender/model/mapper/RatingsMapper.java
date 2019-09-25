package com.cloudservice.movierecommender.model.mapper;

import com.cloudservice.movierecommender.model.bean.Ratings;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "RatingsMapper")
public interface RatingsMapper {
    @Delete({
        "delete from ratings",
        "where idRating = #{idRating,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("idRating") Integer idRating);

    @Insert({
        "insert into ratings (idRating, userId, ",
        "filmId, rating)",
        "values (#{idRating,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{filmId,jdbcType=INTEGER}, #{rating,jdbcType=DOUBLE})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="idRating", before=false, resultType=Integer.class)
    int insert(Ratings record);

    @Select({
        "select",
        "idRating, userId, filmId, rating",
        "from ratings",
        "where idRating = #{idRating,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="idRating", property="idRating", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userId", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="filmId", property="filmId", jdbcType=JdbcType.INTEGER),
        @Result(column="rating", property="rating", jdbcType=JdbcType.DOUBLE)
    })
    Ratings selectByPrimaryKey(@Param("idRating") Integer idRating);

    @Select({
        "select",
        "idRating, userId, filmId, rating",
        "from ratings"
    })
    @Results({
        @Result(column="idRating", property="idRating", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userId", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="filmId", property="filmId", jdbcType=JdbcType.INTEGER),
        @Result(column="rating", property="rating", jdbcType=JdbcType.DOUBLE)
    })
    List<Ratings> selectAll();

    @Update({
        "update ratings",
        "set userId = #{userId,jdbcType=INTEGER},",
          "filmId = #{filmId,jdbcType=INTEGER},",
          "rating = #{rating,jdbcType=DOUBLE}",
        "where idRating = #{idRating,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Ratings record);

    @Update({
            "update ratings",
            "set rating = #{rating,jdbcType=DOUBLE}",
            "where filmId = #{filmId,jdbcType=INTEGER} and userId = #{userId,jdbcType=INTEGER}"
    })
    int updateByFilmIdAndUserId(Ratings record);

    @Select({
            "select avg(rating) from ratings where filmId = #{filmId, jdbcType = INTEGER}"
    })
    Double getFilmAverageRatingById(@Param("filmId") Integer filmId);

    @Select("select rating from ratings where filmId = #{filmId} and userId = #{userId}")
    Double getRatingByFilmIdAndUserId(@Param("filmId") Integer filmId,@Param("userId") Integer userId);
}