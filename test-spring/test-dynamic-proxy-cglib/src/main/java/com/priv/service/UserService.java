package com.priv.service;

import com.priv.entity.User;

public interface UserService {
    boolean addUser(User user);

    boolean updateUser(User user);
}
