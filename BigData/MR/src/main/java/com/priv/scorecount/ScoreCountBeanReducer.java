package com.priv.scorecount;

import com.priv.flowcount.Flow;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ScoreCountBeanReducer extends Reducer<Text, Student, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<Student> values, Context context) throws IOException, InterruptedException {

        int sum = 0;

        for(Student student : values){

            sum += student.getMathScore() + student.getChineseScore() + student.getEnglishScore();
        }

        context.write(key, new IntWritable(sum));
    }
}
