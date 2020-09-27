package com.priv.sortprofit;

import com.priv.partprofit.PartProfitMapper;
import com.priv.partprofit.PartProfitPartitioner;
import com.priv.partprofit.PartProfitReducer;
import com.priv.partprofit.ProfitUser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 统计每个人使用的总流量
 */
public class SortProfitDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取环境参数
        Configuration conf = new Configuration();
        //向Yarn申请任务
        Job job = Job.getInstance(conf);

        //指定Mapper类
        job.setMapperClass(SortProfitMapper.class);
        //无Reducer类
        //指定Driver类
        job.setJarByClass(SortProfitDriver.class);

        //无分区

        //指定Mapper输出结果的KV类型
        //注意：！！！！如果Mapper和Reduce的类型一样，那么可以不写
        job.setMapOutputKeyClass(SortProfitUser.class);
        job.setMapOutputValueClass(NullWritable.class);


        //指定要处理的文件路径
        FileInputFormat.addInputPath(job, new Path("hdfs://10.211.55.5:9000/video2/txt/profit2.txt"));
        //指定结果的输出路径，要求这个路径不存在
        FileOutputFormat.setOutputPath(job, new Path("hdfs://10.211.55.5:9000/result/sortprofit2"));
        //交给yarn去执行，直到执行结束才退出本程序
        job.waitForCompletion(true);
    }
}
