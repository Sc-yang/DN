package com.priv.factory;

import com.priv.ioc.Hello;
import org.springframework.beans.factory.FactoryBean;

public class SpringFactory implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new Hello();
    }

    @Override
    public Class<?> getObjectType() {
        return Hello.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
