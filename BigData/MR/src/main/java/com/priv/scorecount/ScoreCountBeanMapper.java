package com.priv.scorecount;

import com.priv.flowcount.Flow;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ScoreCountBeanMapper extends Mapper<LongWritable, Text, Text, Student> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] strings = value.toString().split(" ");

        Student student = new Student();
        student.setName(strings[0]);
        student.setMathScore(Integer.parseInt(strings[1]));
        student.setChineseScore(Integer.parseInt(strings[2]));
        student.setEnglishScore(Integer.parseInt(strings[3]));

        context.write(new Text(student.getName()), student);
    }
}
