package com.cloudservice.movierecommender.model.bean;

import java.io.Serializable;

public class Ratings implements Serializable {
    private Integer idRating;

    private Integer userId;

    private Integer filmId;

    private Double rating;

    private static final long serialVersionUID = 1L;

    public Integer getIdRating() {
        return idRating;
    }

    public void setIdRating(Integer idRating) {
        this.idRating = idRating;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", idRating=").append(idRating);
        sb.append(", userId=").append(userId);
        sb.append(", filmId=").append(filmId);
        sb.append(", rating=").append(rating);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}