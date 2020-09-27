package com.priv.invert;

import com.priv.flowcount.Flow;
import com.priv.flowcount.FlowDriver;
import com.priv.flowcount.FlowMapper;
import com.priv.flowcount.FlowReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class InvertDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取环境参数
        Configuration conf = new Configuration();
        //向Yarn申请任务
        Job job = Job.getInstance(conf);

        //指定Mapper类
        job.setMapperClass(InvertMapper.class);
        //指定Reducer类
        job.setReducerClass(InvertReducer.class);
        //指定Driver类
        job.setJarByClass(InvertDriver.class);

        //指定合并类
        job.setCombinerClass(InvertReducer.class);

        //指定Reduce输出结果的KV类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);


        //指定要处理的文件路径
        FileInputFormat.addInputPath(job, new Path("hdfs://10.211.55.5:9000/video2/txt/invert"));
        //指定结果的输出路径，要求这个路径不存在
        FileOutputFormat.setOutputPath(job, new Path("hdfs://10.211.55.5:9000/result/invert2"));
        //交给yarn去执行，直到执行结束才退出本程序
        job.waitForCompletion(true);
    }
}
