package com.priv.other;


/**
 * 这是新的业务类，假设是加一个事务功能
 */
public class TransactionManager {

    public void begin(){
        System.out.println("开启事务");
    }

    public void commit(){
        System.out.println("提交事务");
    }

    public void rollback(){
        System.out.println("回滚事务");
    }
}
