package com.priv;

import com.priv.dao.UserDao;
import com.priv.entity.User;


public class UserDaoImpl implements UserDao {
    @Override
    public int addUser(User user) {
        System.out.println("UserDaoImpl.addUser()");
        return 1;
    }

    @Override
    public int updateUser(User user) {
        System.out.println("UserDaoImpl.updateUse r()");
        return 1;
    }
}
