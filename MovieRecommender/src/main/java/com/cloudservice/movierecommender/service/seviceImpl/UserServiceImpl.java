package com.cloudservice.movierecommender.service.seviceImpl;

import com.cloudservice.movierecommender.model.bean.Films;
import com.cloudservice.movierecommender.model.bean.Users;
import com.cloudservice.movierecommender.model.mapper.FilmsMapper;
import com.cloudservice.movierecommender.model.mapper.UsersMapper;
import com.cloudservice.movierecommender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper userMapper;
    @Autowired
    private FilmsMapper filmMapper;

    @Transactional
    @Override
    public Users addUser(Users user) {
        userMapper.insert(user);
        user.setPassword(null);
        return user;
    }

    @Transactional
    @Override
    public List<Films> getUserRatedFilms(Integer userId) {
        return filmMapper.listUserRatedFilms(userId);
    }

    @Transactional
    @Override
    public List<Films> getUserUnratedFilms(Integer userId) {
        return filmMapper.listUserUnratedFilms(userId);
    }

    @Transactional
    @Override
    public Users findById(Integer userId){
        return userMapper.selectByPrimaryKey(userId);
    }
}
