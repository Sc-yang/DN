package com.priv.dao;

import com.priv.entity.User;

public interface UserDao {
    int addUser(User user);
    int updateUser(User user);
}
