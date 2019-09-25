package com.cloudservice.movierecommender.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloudservice.movierecommender.model.bean.Films;
import com.cloudservice.movierecommender.model.bean.Users;
import com.cloudservice.movierecommender.model.mapper.UsersMapper;
import com.cloudservice.movierecommender.service.UserService;
import com.cloudservice.movierecommender.util.Crypto;
import com.cloudservice.movierecommender.util.apiUtil.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cloudservice.movierecommender.util.apiUtil.ResultCode.E_600;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UsersMapper usersMapper;

    @RequestMapping("/insert")
    @ResponseBody
    public CommonResult insert(@RequestBody JSONObject json){
        Users user = new Users();
        //int idUser = (int)json.get("idUser");
        int roleId = (int)json.get("roleId");
        String name = (String)json.get("name");
        String password = Crypto.md5((String)json.get("password"));

        //user.setIdUser(idUser);
        user.setRoleId(roleId);
        user.setName(name);
        user.setPassword(password);

        return CommonResult.success(userService.addUser(user));
    }

    @RequestMapping("/getUserRatedFilms")
    @ResponseBody
    public CommonResult getUserRatedFilms(@RequestBody JSONObject json){
        int userId = (int)json.get("userId");
        Users user = userService.findById(userId);
        if(user==null)
            return CommonResult.fail(E_600);

        List<Films> list = userService.getUserRatedFilms(userId);
        return CommonResult.success(list);
    }

    @RequestMapping("/getUserUnratedFilms")
    @ResponseBody
    public CommonResult getUserUnratedFilms(@RequestBody JSONObject json){
        int userId = (int)json.get("userId");
        Users user = userService.findById(userId);
        if(user==null)
            return CommonResult.fail(E_600);

        List<Films> list = userService.getUserUnratedFilms(userId);
        return CommonResult.success(list);
    }

    @RequestMapping("/login")
    @ResponseBody
    public CommonResult login(@RequestBody JSONObject json){
        int userId = (int)json.get("userId");
        String password = (String)json.get("password");
        Users user = usersMapper.selectByPrimaryKey(userId);
        return CommonResult.success(user.getName());
    }
}
