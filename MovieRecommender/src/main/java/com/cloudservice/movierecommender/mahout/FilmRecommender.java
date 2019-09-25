package com.cloudservice.movierecommender.mahout;

import com.cloudservice.movierecommender.service.RecommendationService;
import com.cloudservice.movierecommender.util.FileUtil;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class FilmRecommender {

    public static GenericUserBasedRecommender recommender = null;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    FileUtil fileUtil;

    private GenericUserBasedRecommender buildRecommender(File file){
        DataModel model = null;
        try{
            model = new FileDataModel(file);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        UserSimilarity similarity = null;
        try{
            similarity = new PearsonCorrelationSimilarity(model);
        }catch (TasteException ex){
            ex.printStackTrace();
        }
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0, similarity, model);
        recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        return recommender;
    }


    public GenericUserBasedRecommender getRecommender(){
        if(recommender != null){
            return recommender;
        }else{
            recommender = buildRecommender(fileUtil.getParsedDataFile());
            return recommender;
        }
    }

    public void renewRecomender(){
        recommender = buildRecommender((fileUtil.getParsedDataFile()));
    }


}
