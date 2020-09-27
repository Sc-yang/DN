package com.priv.staticproxy;

import com.priv.entity.User;
import com.priv.other.TransactionManager;
import com.priv.service.UserService;

/**
 * 此类为静态代理类
 * 用于老业务和新业务的耦合
 * 必须实现老业务的接口，保证功能不丢失
 */
public class StaticProxy implements UserService {

    //老业务对象，调用老业务api
    private UserService userService;
    //新业务对象，假设为事务业务
    private TransactionManager transactionManager;

    public StaticProxy(UserService userService, TransactionManager transactionManager) {
        this.userService = userService;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean addUser(User user) {
        boolean flag = false;

        try{
            transactionManager.begin();
            flag = userService.addUser(user);
            transactionManager.commit();
        }catch(Exception e){
            e.printStackTrace();
            transactionManager.rollback();
        }
        return flag;
    }

    @Override
    public boolean updateUser(User user) {
        boolean flag = false;

        try{
            transactionManager.begin();
            flag = userService.updateUser(user);
            transactionManager.commit();
        }catch(Exception e){
            e.printStackTrace();
            transactionManager.rollback();
        }
        return flag;
    }
}
