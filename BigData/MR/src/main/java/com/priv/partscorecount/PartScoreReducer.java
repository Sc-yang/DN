package com.priv.partscorecount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PartScoreReducer extends Reducer<Text,PartStudent,Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<PartStudent> values, Context context) throws IOException, InterruptedException {

        int sum = 0;

        for(PartStudent value : values){
            sum += value.getScore();
        }

        context.write(key,new IntWritable(sum));
    }
}
