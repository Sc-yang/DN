package com.priv.mytest.highscore;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class HighScoreReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        IntWritable max = new IntWritable(0);
        //在MapReduce中为了节省内存空间，采用了地址复用机制
        //在迭代过程中，为了节省空间，只有一个对象，每一次迭代的值都往这一个对象放
        // key = Bob, values = 684 340 312 548
        //迭代遍历
        //IntWritable val = new IntWritable();
        //val.set(684);
        //val.get>max.get
        //max = val; -- IntWritable是一个对象，赋值的是地址
        //val.set(340) …………
        for(IntWritable intWritable : values){
            if(intWritable.get() > max.get()){

                //不能写成max = intWritable; 因为MapReduce有地址的复用
                // intWritable始终只有一个对象，只有一个地址，max最终会获取到values集合中最后的那个intWritable值
                //如果顺序是 684 340 312 548  那么max是548
                //如果顺序是 684 340 548 312  那么max是312

                max.set(intWritable.get());
            }
        }

        context.write(key, max);
    }
}
