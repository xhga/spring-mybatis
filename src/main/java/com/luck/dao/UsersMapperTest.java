package com.luck.dao;

import com.luck.api.UsersManager;
import com.luck.pojo.Record;
import com.luck.pojo.Users;
import com.luck.service.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Hua wb on 2018/7/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config-db.xml"})
public class UsersMapperTest {
    @Autowired
    UsersMapper usersMapper;
    @Autowired
    UsersService usersService;
    @Autowired
    UsersManager usersManager;
    @Autowired
    RecordMapper recordMapper;

    @Test
    public void selectByPrimaryKey() throws Exception {
        Users users = usersMapper.selectByPrimaryKey(1);
        System.out.println(users);
    }
    @Test
    public void usersService() throws Exception {
       /* Users users = new Users();
        users.setAge(18);
        users.setName("hwb");
        int i = usersMapper.insertSelective(users);*/
       Record record = new Record();
       record.setId(1);
       record.setContent("hahaha");
        int i = recordMapper.insertSelective(record);
        System.out.println(i);
    }

    @Test
    public void usersManager() throws Exception {
        Users users = usersManager.getUser(1);
        System.out.println(users);
    }


}
