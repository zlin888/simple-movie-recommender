package com.cloudservice.movierecommender.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloudservice.movierecommender.model.bean.Films;
import com.cloudservice.movierecommender.model.bean.Users;
import com.cloudservice.movierecommender.service.FilmService;
import com.cloudservice.movierecommender.service.RecommendationService;
import com.cloudservice.movierecommender.service.UserService;
import com.cloudservice.movierecommender.util.apiUtil.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.cloudservice.movierecommender.util.apiUtil.ResultCode.E_550;
import static com.cloudservice.movierecommender.util.apiUtil.ResultCode.E_600;

@RestController
@CrossOrigin
@RequestMapping("/recommendation")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private UserService userService;

    @Autowired
    private FilmService filmService;

    @RequestMapping("/getPrediction")
    @ResponseBody
    public CommonResult getPrediction(@RequestBody JSONObject json){
        int userId = (int)json.get("userId");
        int filmId = (int)json.get("filmId");

        Users user = userService.findById(userId);
        if(user==null)
            return CommonResult.fail(E_600);
        Films film = filmService.getFilmById(filmId);
        if(film==null)
            return CommonResult.fail(E_550);

        double prediction = recommendationService.getPrediction(userId,filmId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("prediction",prediction);
        return CommonResult.success(jsonObject);
    }
}
