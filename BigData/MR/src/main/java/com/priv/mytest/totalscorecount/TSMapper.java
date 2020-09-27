package com.priv.mytest.totalscorecount;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TSMapper extends Mapper<LongWritable ,Text ,Text , IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] strings = value.toString().split(" ");
        context.write(new Text(strings[0]), new IntWritable(Integer.parseInt(strings[1])));
    }
}
