package com.priv.object;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public boolean addUser(User user) {
        System.out.println("UserServiceImpl.addUser");
        userDao.addUser(user);
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        System.out.println("UserServiceImpl.updateUser");
        userDao.updateUser(user);
        return false;
    }
}
