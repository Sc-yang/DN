package com.priv.mytest.ip;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 找出不重复的ip
 */
public class IPDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        //指定Mapper类
        job.setMapperClass(IPMapper.class);
        //指定Reducer类
        job.setReducerClass(IPReducer.class);
        //指定Driver类
        job.setJarByClass(IPDriver.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);


        //指定要处理的文件路径
        FileInputFormat.addInputPath(job, new Path("hdfs://10.211.55.5:9000/video2/txt/ip.txt"));
        //指定结果的输出路径，要求这个路径不存在
        FileOutputFormat.setOutputPath(job, new Path("hdfs://10.211.55.5:9000/result/ip2"));
        //交给yarn去执行，直到执行结束才退出本程序
        job.waitForCompletion(true);
    }
}
