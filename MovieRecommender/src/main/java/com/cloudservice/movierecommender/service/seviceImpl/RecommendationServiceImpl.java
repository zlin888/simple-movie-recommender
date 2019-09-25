package com.cloudservice.movierecommender.service.seviceImpl;

import com.cloudservice.movierecommender.mahout.FilmRecommender;
import com.cloudservice.movierecommender.service.RecommendationService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private FilmRecommender filmRecommender;

    @Transactional
    @Override
    public double getPrediction(int userId, int filmId){
        double prediction = -1.0;
        try{
            GenericUserBasedRecommender recommender = filmRecommender.getRecommender();
            prediction = (recommender.estimatePreference(userId, filmId));
            if(Double.isNaN(prediction)){
                prediction = -1.0;
            }
        }catch (TasteException ex){
            ex.printStackTrace();;
            prediction = - 1.0;
        }
        return prediction;
    }
}
