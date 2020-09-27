package com.priv.customformat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AuthMapper extends Mapper<Text, Text, Text, IntWritable> {
    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        String[] strings = value.toString().split(" ");

        int sum = 0;

        sum += Integer.parseInt(strings[1]);
        sum += Integer.parseInt(strings[3]);

        context.write(key,new IntWritable(sum));

    }
}
