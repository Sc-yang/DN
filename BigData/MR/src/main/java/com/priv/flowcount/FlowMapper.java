package com.priv.flowcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable, Text, Text, Flow> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] strings = value.toString().split(" ");

        Flow flow = new Flow();
        flow.setPhoneNum(strings[0]);
        flow.setAddr(strings[1]);
        flow.setName(strings[2]);
        flow.setFlow(Integer.parseInt(strings[3]));

        context.write(new Text(flow.getName()), flow);
    }
}
