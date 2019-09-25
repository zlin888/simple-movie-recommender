package com.cloudservice.movierecommender.service.seviceImpl;

import com.cloudservice.movierecommender.mahout.FilmRecommender;
import com.cloudservice.movierecommender.model.bean.Films;
import com.cloudservice.movierecommender.model.mapper.FilmsMapper;
import com.cloudservice.movierecommender.service.FilmService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRecommender filmRecommender;

    @Autowired
    private FilmsMapper filmMapper;


    @Transactional
    @Override
    public List<Films> listAllFilms() {
        return filmMapper.selectAll();
    }

    @Transactional
    @Override
    public void updateAvgRatings() {
        List<Integer> filmIds = filmMapper.listAllFilmIds();
        for(Integer filmId: filmIds){
            filmMapper.updateAvgRatingById(filmId);
        }
    }

    @Transactional
    @Override
    public Films getFilmById(int filmId) {
        return filmMapper.selectByPrimaryKey(filmId);
    }

    @Transactional
    @Override
    public List<Films> listTopRecommended(int userId, int howMuch) {
        List<RecommendedItem> recommendedItems = new ArrayList();
        List<Films> recommendedFilms = new ArrayList();
        try{
            recommendedItems = filmRecommender.getRecommender().recommend(userId, howMuch);
        }catch (TasteException ex){
            ex.printStackTrace();
        }
        if(!recommendedItems.isEmpty()){
            for(RecommendedItem item: recommendedItems){
                Films film = getFilmById((int)item.getItemID());
                recommendedFilms.add(film);
            }
        }
        return recommendedFilms;
    }

    @Transactional
    @Override
    public List<Films> listTopNFilmsWithAvgRating(int userId, int n) {
        return filmMapper.listTopNFilmsWithAvgRating(userId, n);
    }
}
