package com.priv.factory;

import com.priv.ioc.Hello;

public class StaticFactory {

    /**
     * 生产对象的静态方法
     * @return
     */
    public static Hello getObject(){
        return new Hello();
    }
}
