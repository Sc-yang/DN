package com.priv.mytest.charactercount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CharactersCountMapper extends Mapper<LongWritable ,Text ,Text , LongWritable>{

    @Override
    protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {
        char[] chars = value.toString().toCharArray();

        for (char c : chars){
            // context 是 Hadoop中的上下文，hadoop计算结果用KV的形式存如下
            context.write(new Text(String.valueOf(c)), new LongWritable(1));
        }
    }
}
