package com.priv.service.impl;

import com.priv.UserDaoImpl;
import com.priv.dao.UserDao;
import com.priv.entity.User;
import com.priv.service.UserService;

public class UserServiceImpl implements UserService {


    private UserDao userDao = new UserDaoImpl();


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
