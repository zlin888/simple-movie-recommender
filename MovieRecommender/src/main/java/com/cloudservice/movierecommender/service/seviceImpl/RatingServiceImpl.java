package com.cloudservice.movierecommender.service.seviceImpl;

import com.cloudservice.movierecommender.model.bean.Ratings;
import com.cloudservice.movierecommender.model.mapper.RatingsMapper;
import com.cloudservice.movierecommender.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingsMapper ratingMapper;

    @Transactional
    @Override
    public Ratings insertRating(Ratings rating) {
        //check if rating exists
        Double oldRating = ratingMapper.getRatingByFilmIdAndUserId(rating.getFilmId(), rating.getUserId());
        if(oldRating != null){
            //update rating
            ratingMapper.updateByFilmIdAndUserId(rating);
        }else {
            //insert new rating
            ratingMapper.insert(rating);
        }
        return rating;
    }

    @Transactional
    @Override
    public Double getRating(Integer filmId, Integer userId) {
        return ratingMapper.getRatingByFilmIdAndUserId(filmId, userId);
    }
}
