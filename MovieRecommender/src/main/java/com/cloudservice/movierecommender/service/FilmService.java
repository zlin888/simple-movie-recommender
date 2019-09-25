package com.cloudservice.movierecommender.service;

import com.cloudservice.movierecommender.model.bean.Films;

import java.util.List;

public interface FilmService {

    List<Films> listAllFilms();

    void updateAvgRatings();

    Films getFilmById(int filmId);

    List<Films> listTopRecommended(int userId, int howMany);

    List<Films> listTopNFilmsWithAvgRating(int userId, int n);

}
