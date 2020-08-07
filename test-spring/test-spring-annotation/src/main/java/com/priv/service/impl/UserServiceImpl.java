package com.priv.service.impl;

import com.priv.dao.UserDao;
import com.priv.entity.User;
import com.priv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

//    @Autowired
    @Resource(name="userDao")
    private UserDao userDao;


    @Override
    public boolean addUser(User user) {
        System.out.println("UserServiceImpl.addUser");
        boolean flag = false;
        int rowAffect = userDao.addUser(user);

        if(rowAffect == 1){
            flag = true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        System.out.println("UserServiceImpl.updateUser");
        boolean flag = false;
        int rowAffect = userDao.addUser(user);

        if(rowAffect == 1){
            flag = true;
        }
        return false;
    }
}
