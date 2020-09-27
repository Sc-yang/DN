package com.priv.partscorecount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PartScoreCountMapper extends Mapper<LongWritable, Text, Text, PartStudent> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] strings = value.toString().split(" ");

        PartStudent partStudent = new PartStudent();
        partStudent.setMonth(Integer.parseInt(strings[0]));
        partStudent.setName(strings[1]);
        partStudent.setScore(Integer.parseInt(strings[2]));

        context.write(new Text(partStudent.getName()), partStudent);
    }
}
