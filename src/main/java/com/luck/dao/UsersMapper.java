package com.luck.dao;

import com.luck.interceptor.TableSplit;
import com.luck.pojo.Users;
import org.springframework.stereotype.Repository;

@TableSplit(value = "users", strategy = "MM")
public interface UsersMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}