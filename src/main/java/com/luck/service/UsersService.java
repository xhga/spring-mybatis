package com.luck.service;

import com.luck.api.UsersManager;
import com.luck.dao.UsersMapper;
import com.luck.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Hua wb on 2018/7/26.
 */
@Service
public class UsersService implements UsersManager {
    @Autowired
    UsersMapper usersMapper;
    @Override
    public Users getUser(int uid) {
        return usersMapper.selectByPrimaryKey(uid);
    }
}
