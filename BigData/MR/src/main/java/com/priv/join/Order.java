package com.priv.join;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Order implements Writable {

    //两个文件里的所有字段
    private String orderid = "";
    private String date = "";
    private String proid = "";
    private int num;
    private String name = "";
    private int price;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(orderid);
        dataOutput.writeUTF(date);
        dataOutput.writeUTF(proid);
        dataOutput.writeInt(num);
        dataOutput.writeUTF(name);
        dataOutput.writeInt(price);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.orderid = dataInput.readUTF();
        this.date = dataInput.readUTF();
        this.proid = dataInput.readUTF();
        this.num = dataInput.readInt();
        this.name = dataInput.readUTF();
        this.price = dataInput.readInt();
    }
}
