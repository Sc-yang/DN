package com.priv.other;

import com.priv.ioc.Hello;

public class OtherIOCDI {

    private String name;
    private Hello hello;

    public void setName(String name) {
        this.name = name;
    }

    public void setHello(Hello hello) {
        this.hello = hello;
    }

    @Override
    public String toString() {
        return "OtherIOCDI{" +
                "name='" + name + '\'' +
                ", hello=" + hello +
                '}';
    }
}
