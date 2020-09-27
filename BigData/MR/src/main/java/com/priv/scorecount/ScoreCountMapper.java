package com.priv.scorecount;

import com.priv.flowcount.Flow;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ScoreCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] strings = value.toString().split(" ");

        int sum = Integer.parseInt(strings[1]) + Integer.parseInt(strings[2]) + Integer.parseInt(strings[3]);

        context.write(new Text(strings[0]), new IntWritable(sum));
    }
}
