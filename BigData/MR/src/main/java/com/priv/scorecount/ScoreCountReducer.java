package com.priv.scorecount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.Logger;

import java.io.IOException;

public class ScoreCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private static Logger LOGGER = Logger.getLogger(ScoreCountReducer.class);

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {



        //Hadoop中 sout无法输出！！
        System.out.println("sout : " + values.toString());
        LOGGER.info("log : " + values.toString());

        String result = values.iterator().next().toString();

        System.out.println("sout : " + result);
        LOGGER.info("log : " + result);

        context.write(key, new IntWritable(Integer.parseInt(result)));
    }
}
