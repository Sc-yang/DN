package com.priv.mytest.charactercount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


/**
 * 统计每个字符出现的次数
 */
public class CharactersCountDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        //指定Mapper类
        job.setMapperClass(CharactersCountMapper.class);
        //指定Reducer类
        job.setReducerClass(CharactersCountReducer.class);
        //指定Driver类
        job.setJarByClass(CharactersCountDriver.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);


        //指定要处理的文件路径
        FileInputFormat.addInputPath(job, new Path("hdfs://10.211.55.5:9000/video2/txt/characters.txt"));
        //指定结果的输出路径，要求这个路径不存在
        FileOutputFormat.setOutputPath(job, new Path("hdfs://10.211.55.5:9000/characters"));
        //交给yarn去执行，直到执行结束才退出本程序
        job.waitForCompletion(true);
    }

}
