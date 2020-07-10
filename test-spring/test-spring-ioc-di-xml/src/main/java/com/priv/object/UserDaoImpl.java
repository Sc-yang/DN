package com.priv.object;

public class UserDaoImpl implements UserDao {
    @Override
    public int addUser(User user) {
        System.out.println("UserDaoImpl.addUser()");
        return 0;
    }

    @Override
    public int updateUser(User user) {
        System.out.println("UserDaoImpl.updateUser()");
        return 0;
    }
}
