package com.priv.flowcount;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Flow implements Writable {
    private String phoneNum = "";
    private String name = "";
    private String addr = "";
    private int flow;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    //序列化
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(phoneNum);
        dataOutput.writeUTF(name);
        dataOutput.writeUTF(addr);
        dataOutput.writeInt(flow);
    }

    //反序列化
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.phoneNum = dataInput.readUTF();
        this.name = dataInput.readUTF();
        this.addr = dataInput.readUTF();
        this.flow = dataInput.readInt();
    }
}
