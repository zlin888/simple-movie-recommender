package com.cloudservice.movierecommender.service;

public interface RecommendationService {

    double getPrediction(int userId, int filmId);

}


