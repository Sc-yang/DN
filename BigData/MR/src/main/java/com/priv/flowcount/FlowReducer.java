package com.priv.flowcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowReducer extends Reducer<Text, Flow, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<Flow> values, Context context) throws IOException, InterruptedException {

        int sum = 0;

        for(Flow flow : values){
            sum += flow.getFlow();
        }

        context.write(key, new IntWritable(sum));
    }
}
