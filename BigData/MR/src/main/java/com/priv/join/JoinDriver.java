package com.priv.join;

import com.priv.invert.InvertMapper;
import com.priv.invert.InvertReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;

public class JoinDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取环境参数
        Configuration conf = new Configuration();
        //向Yarn申请任务
        Job job = Job.getInstance(conf);

        //指定Mapper类
        job.setMapperClass(JoinMapper.class);
        //指定Reducer类
        job.setReducerClass(JoinReducer.class);
        //指定Driver类
        job.setJarByClass(JoinDriver.class);

        //将小文件进行缓存
        //这次只缓存一个文件，数组大小给1就行
        URI[] files = new URI[1];
        files[0] = URI.create("hdfs://10.211.55.5:9000/video2/txt/union/product.txt");
        job.setCacheFiles(files);

        //指定Map输出结果格式
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Order.class);

        //指定Reduce输出结果的KV类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);


        //指定要处理的文件路径
        FileInputFormat.addInputPath(job, new Path("hdfs://10.211.55.5:9000/video2/txt/union/order.txt"));
        //指定结果的输出路径，要求这个路径不存在
        FileOutputFormat.setOutputPath(job, new Path("hdfs://10.211.55.5:9000/result/join"));
        //交给yarn去执行，直到执行结束才退出本程序
        job.waitForCompletion(true);
    }
}
