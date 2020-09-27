package com.priv.sortprofit;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class SortProfitUser implements WritableComparable<SortProfitUser> {

    private int month;
    private String name = "";
    private int profile;

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

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    @Override
    public int compareTo(SortProfitUser o) {
        //升序
        int r1 = this.month - o.month;

        if(r1 == 0){
            int r2 = o.profile - this.profile;
            //降序
            return r2 == 0 ? 1 : r2;
        }

        return r1;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(month);
        dataOutput.writeUTF(name);
        dataOutput.writeInt(profile);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.month = dataInput.readInt();
        this.name = dataInput.readUTF();
        this.profile = dataInput.readInt();
    }


    /**
     * 对于这个程序的写法，重写toString才能在文件中展示结果，不然文件中写入的都是地址
     * 而且这些地址还都相同，因为Hadoop的地址复用机制
     * @return
     */
    @Override
    public String toString() {
        return "SortProfitUser{" +
                "month=" + month +
                ", name='" + name + '\'' +
                ", profile=" + profile +
                '}';
    }
}
