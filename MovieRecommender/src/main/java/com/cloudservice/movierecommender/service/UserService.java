package com.cloudservice.movierecommender.service;

import com.cloudservice.movierecommender.model.bean.Films;
import com.cloudservice.movierecommender.model.bean.Users;

import java.util.List;

public interface UserService {

    Users addUser(Users user);

    List<Films> getUserRatedFilms(Integer userId);

    List<Films> getUserUnratedFilms(Integer userId);

    Users findById(Integer userId);

}
