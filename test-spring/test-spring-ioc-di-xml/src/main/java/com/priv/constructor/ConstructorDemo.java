package com.priv.constructor;

import com.priv.ioc.Hello;

public class ConstructorDemo {
    private Hello hello;
    private String name;

    public ConstructorDemo(String name, Hello hello){
        this.name = name;
        this.hello = hello;
    }

    @Override
    public String toString() {
        return "ConstructorDemo{" +
                "hello=" + hello +
                ", name='" + name + '\'' +
                '}';
    }
}
