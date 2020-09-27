package com.priv.partprofit;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ProfitUser implements Writable {
    private int month;
    private String name = "";
    private int income;
    private int expend;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getExpend() {
        return expend;
    }

    public void setExpend(int expend) {
        this.expend = expend;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(month);
        dataOutput.writeUTF(name);
        dataOutput.writeInt(income);
        dataOutput.writeInt(expend);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.month = dataInput.readInt();
        this.name = dataInput.readUTF();
        this.income = dataInput.readInt();
        this.expend = dataInput.readInt();
    }
}
