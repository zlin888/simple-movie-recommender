package com.cloudservice.movierecommender.model.mapper;

import com.cloudservice.movierecommender.model.bean.Films;
import java.util.List;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "FilmsMapper")
public interface FilmsMapper {
    @Delete({
        "delete from films",
        "where idFilm = #{idFilm,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer idFilm);

    @Insert({
        "insert into films (idFilm, title, ",
        "yr, avgRating, poster)",
        "values (#{idFilm,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
        "#{yr,jdbcType=INTEGER}, #{avgRating,jdbcType=DOUBLE}, #{poster,jdbcType=LONGVARCHAR})"
    })
    int insert(Films record);

    @Select({
        "select",
        "idFilm, title, yr, avgRating, poster",
        "from films",
        "where idFilm = #{idFilm,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="idFilm", property="idFilm", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="yr", property="yr", jdbcType=JdbcType.INTEGER),
        @Result(column="avgRating", property="avgRating", jdbcType=JdbcType.DOUBLE),
        @Result(column="poster", property="poster", jdbcType=JdbcType.LONGVARCHAR)
    })
    Films selectByPrimaryKey(Integer idFilm);

    @Select({
        "select",
        "idFilm, title, yr, avgRating, poster",
        "from films"
    })
    @Results({
        @Result(column="idFilm", property="idFilm", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="yr", property="yr", jdbcType=JdbcType.INTEGER),
        @Result(column="avgRating", property="avgRating", jdbcType=JdbcType.DOUBLE),
        @Result(column="poster", property="poster", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Films> selectAll();

    @Update({
        "update films",
        "set title = #{title,jdbcType=VARCHAR},",
          "yr = #{yr,jdbcType=INTEGER},",
          "avgRating = #{avgRating,jdbcType=DOUBLE},",
          "poster = #{poster,jdbcType=LONGVARCHAR}",
        "where idFilm = #{idFilm,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Films record);

    @Select({
            "select * ",
            "from Films ",
            "where Films.idFilm in ( select filmId from Ratings where Ratings.userId = #{userId})"
    })
    @Results({
            @Result(column="idFilm", property="idFilm", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="yr", property="yr", jdbcType=JdbcType.INTEGER),
            @Result(column="avgRating", property="avgRating", jdbcType=JdbcType.DOUBLE),
            @Result(column="poster", property="poster", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Films> listUserRatedFilms(Integer userId);

    @Select({
            "select * ",
            "from Films ",
            "where Films.idFilm not in ( select filmId from Ratings where Ratings.userId = #{userId})"
    })
    @Results({
            @Result(column="idFilm", property="idFilm", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="yr", property="yr", jdbcType=JdbcType.INTEGER),
            @Result(column="avgRating", property="avgRating", jdbcType=JdbcType.DOUBLE),
            @Result(column="poster", property="poster", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Films> listUserUnratedFilms(Integer userId);

    @Select("select idFilm from Films")
    List<Integer> listAllFilmIds();

    @Select({
            "select * from Films ",
            "where idFilm not in (select filmId from ratings where userId = #{userId}) ",
            "order by avgRating DESC limit #{limit}"
    })
    @Results({
            @Result(column="idFilm", property="idFilm", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="yr", property="yr", jdbcType=JdbcType.INTEGER),
            @Result(column="avgRating", property="avgRating", jdbcType=JdbcType.DOUBLE),
            @Result(column="poster", property="poster", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Films> listTopNFilmsWithAvgRating(Integer userId, Integer limit);

    @Update({
            "update Films ",
            "set avgRating = (",
            "select avg(rating) from Ratings where filmId = #{idFilm, jdbcType = INTEGER}) ",
            "where idFilm = #{idFilm, jdbcType = INTEGER}"
    })
    int updateAvgRatingById(Integer idFilm);
}