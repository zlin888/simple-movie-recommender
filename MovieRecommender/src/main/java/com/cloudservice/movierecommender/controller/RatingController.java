package com.cloudservice.movierecommender.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloudservice.movierecommender.model.bean.Films;
import com.cloudservice.movierecommender.model.bean.Ratings;
import com.cloudservice.movierecommender.model.bean.Users;
import com.cloudservice.movierecommender.service.FilmService;
import com.cloudservice.movierecommender.service.RatingService;
import com.cloudservice.movierecommender.service.UserService;
import com.cloudservice.movierecommender.util.apiUtil.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.cloudservice.movierecommender.util.apiUtil.ResultCode.E_550;
import static com.cloudservice.movierecommender.util.apiUtil.ResultCode.E_600;

@RestController
@CrossOrigin
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @Autowired
    FilmService filmService;

    @Autowired
    UserService userService;

    @RequestMapping("/insert")
    @ResponseBody
    public CommonResult insert(@RequestBody JSONObject json){
        Ratings rating = new Ratings();
        int userId = (int)json.get("userid");
        int filmId = (int)json.get("filmid");
        double ratingValue = Double.parseDouble(json.get("rating").toString());

        Films film = filmService.getFilmById(filmId);
        if(film==null)
            return CommonResult.fail(E_550);
        Users user = userService.findById(userId);
        if(user==null)
            return CommonResult.fail(E_600);

        rating.setFilmId(filmId);
        rating.setUserId(userId);
        rating.setRating(ratingValue);

        return CommonResult.success(ratingService.insertRating(rating));
    }

    @RequestMapping("/getRating")
    @ResponseBody
    public CommonResult getRating(@RequestBody JSONObject json){
        int userId = (int)json.get("userId");
        int filmId = (int)json.get("filmId");

        Films film = filmService.getFilmById(filmId);
        if(film==null)
            return CommonResult.fail(E_550);
        Users user = userService.findById(userId);
        if(user==null)
            return CommonResult.fail(E_600);

        Double rate = ratingService.getRating(filmId,userId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rating",rate);
        return CommonResult.success(jsonObject);
    }
}
