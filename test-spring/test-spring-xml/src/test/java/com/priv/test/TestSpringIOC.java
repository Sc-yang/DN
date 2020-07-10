package com.priv.test;

import com.priv.ioc.Hello;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringIOC {

    @Test
    public void testMethod(){
        // 初始化spring容器，加载spring清单文件
        /*

         */
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        //从spring容器中取出对象
        Hello hello = (Hello) context.getBean("hello");
        Hello hello1 = context.getBean("hello",Hello.class);
        hello.sayHello("孙晨阳");
        hello1.sayHello("sunchenyang");

        System.out.println(hello == hello1);

    }

    /*
    静态工厂方式
     */
    @Test
    public void test2(){

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring_staticfactory.xml");
        Hello hello = context.getBean("hello",Hello.class);
        hello.sayHello("sun");
        context.close();
    }

    /*
    实例工厂方式
     */
    @Test
    public void test3(){

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring_instancefactory.xml");

        Hello hello = context.getBean("hello", Hello.class);
        hello.sayHello("sun");

        context.close();

    }

    /*
    spring工厂
     */
    @Test
    public void test4(){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring_springfactory.xml");
        Hello hello = context.getBean("hello2", Hello.class);
        hello.sayHello("sun");
        context.close();

    }

}
