package com.luck.controller;

import com.luck.api.UsersManager;
import com.luck.pojo.Users;
import com.luck.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Hua wb on 2018/7/26.
 */
@Controller
public class UsersController {
    @Resource
    private UsersService usersService;
    @RequestMapping("/user")
    @ResponseBody
    public String hello(){
        Users user = usersService.getUser(1);
        if (user == null){
            return "hahaha not find!";
        }
        return user.getName();
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
