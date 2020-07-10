package com.priv.ioc;

public class Hello {

    public void init(){
        System.out.println("init");
    }

    public void destroy(){
        System.out.println("destroy");
    }

    public void sayHello(String name){
        System.out.println("hello " + name + "!");
    }
}
