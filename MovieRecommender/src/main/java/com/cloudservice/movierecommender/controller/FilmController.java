package com.cloudservice.movierecommender.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloudservice.movierecommender.model.bean.Films;
import com.cloudservice.movierecommender.service.FilmService;
import com.cloudservice.movierecommender.util.apiUtil.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cloudservice.movierecommender.util.apiUtil.ResultCode.E_550;

@RestController
@CrossOrigin
@RequestMapping("/film")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @RequestMapping("/listAll")
    @ResponseBody
    public CommonResult list(){
        JSONObject jsonObject = new JSONObject();
        List<Films> list = filmService.listAllFilms();
        if(list!=null) {
            jsonObject.put("films", list);
            return CommonResult.success(jsonObject);
        }else {
            return CommonResult.fail(E_550);
        }
    }

    @RequestMapping("/updateAvgRatings")
    @ResponseBody
    public CommonResult updateAvgRatings(){
        filmService.updateAvgRatings();
        return CommonResult.success(filmService.listAllFilms());
    }

    @RequestMapping("/search")
    @ResponseBody
    public CommonResult search(@RequestBody JSONObject json){
        int filmId = (int)json.get("filmId");
        JSONObject jsonObject = new JSONObject();
        Films film = filmService.getFilmById(filmId);
        if(film!=null) {
            jsonObject.put("film", film);
            return CommonResult.success(jsonObject);
        }else{
            return CommonResult.fail(E_550);
        }
    }

    @RequestMapping("/listTopRecommend")
    @ResponseBody
    public CommonResult listTopRecommend(@RequestBody JSONObject json){
        int userId = (int)json.get("userId");
        int howMany = (int)json.get("howMany");
        JSONObject jsonObject = new JSONObject();
        List<Films> list = filmService.listTopRecommended(userId,howMany);
        if(list!=null) {
            jsonObject.put("films", list);
            return CommonResult.success(jsonObject);
        }else{
            return CommonResult.fail(E_550);
        }
    }

    @RequestMapping("/listTopNFilms")
    @ResponseBody
    public CommonResult listTopNFilms(@RequestBody JSONObject json){
        int userId = (int)json.get("userId");
        int n = (int)json.get("n");
        JSONObject jsonObject = new JSONObject();
        List<Films> list = filmService.listTopNFilmsWithAvgRating(userId,n);
        if(list!=null) {
            jsonObject.put("films", list);
            return CommonResult.success(jsonObject);
        }else{
            return CommonResult.fail(E_550);
        }
    }
}
