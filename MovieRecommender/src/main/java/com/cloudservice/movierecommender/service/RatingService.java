package com.cloudservice.movierecommender.service;

import com.cloudservice.movierecommender.model.bean.Ratings;

public interface RatingService {

    Ratings insertRating (Ratings ratings);

    Double getRating(Integer filmId, Integer userId);

}
