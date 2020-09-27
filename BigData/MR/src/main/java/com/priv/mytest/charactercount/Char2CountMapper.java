package com.priv.mytest.charactercount;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class Char2CountMapper extends Mapper<LongWritable ,Text ,Text , LongWritable>{

    @Override
    protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {
        char[] chars = value.toString().toCharArray();

        for (char c : chars){

            if(Character.isLetter(c)){
                context.write(new Text("letter"), new LongWritable(1));
            }else{
                context.write(new Text("not letter"), new LongWritable(1));
            }

        }
    }
}
