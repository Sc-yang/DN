package com.priv.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 想在本地电脑直接跑Hadoop程序
 * 需要解压hadoop安装包到本地电脑，然后将hadoopbin_for_hadoop2.7.1.zip解压到hadoop-2.7.1/bin中
 * 然后配置环境变量
 * export HADOOP_HOME=/Users/sun/Documents/hadoop-2.6.5
 * export PATH=$PATH:$HADOOP_HOME/bin
 *
 * 配置查看 - 视频hadoop02下午 最后部分
 */
public class WordCountDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        //获取环境参数
        Configuration conf = new Configuration();
        //向Yarn申请任务
        Job job = Job.getInstance(conf);

        //指定Mapper类
        job.setMapperClass(WordCountMapper.class);
        //指定Reducer类
        job.setReducerClass(WordCountReduce.class);
        //指定Driver类
        job.setJarByClass(WordCountDriver.class);

        //指定Mapper输出结果的KV类型
        //注意：！！！！如果Mapper和Reduce的类型一样，那么可以不写
        //job.setMapOutputKeyClass(Text.class);
        //job.setMapOutputValueClass(LongWritable.class);

        //指定Reduce输出结果的KV类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);


        //指定要处理的文件路径
        FileInputFormat.addInputPath(job, new Path("hdfs://10.211.55.5:9000/video2/txt/words.txt"));
        //指定结果的输出路径，要求这个路径不存在
        FileOutputFormat.setOutputPath(job, new Path("hdfs://10.211.55.5:9000/wordcount3"));
        //交给yarn去执行，直到执行结束才退出本程序
        job.waitForCompletion(true);

    }
}
