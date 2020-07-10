package com.sun;

import avro.pojo.User;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class AvroSerialDemo {

    @Test
    public void create(){
        User u1 = new User();
        u1.setName("Amy");
        u1.setAge(13);
        u1.setGender(true);

        System.out.println(u1);

        User u2 = new User("David", 13, false);
        System.out.println(u2);

        // 建造者模式
        User u3 = User.newBuilder(u2).setName("Lucy").build();
        System.out.println(u3);
    }

    @Test
    public void serial() throws IOException {
        User u1 = new User("Amy",15,false);
        User u2 = new User("Tom",17,true);

        //创建序列化流
        DatumWriter<User> dw = new SpecificDatumWriter<>(User.class);
        //将序列化的数据写到文件中
        DataFileWriter<User> df = new DataFileWriter<>(dw);

        df.create(User.SCHEMA$, new File("a.txt"));

        //写数据
        df.append(u1);
        df.append(u2);

        //关流
        df.close();
    }

    @Test
    public void deSerial() throws IOException {

        //创建反序列化流
        DatumReader<User> dr = new SpecificDatumReader<>(User.class);
        //创建文件流来从文件中读取数据
        DataFileReader<User> df = new DataFileReader<User>(new File("a.txt"),dr);

        while(df.hasNext()){
            User user = df.next();
            System.out.println(user);
        }

        df.close();



    }



}
