package com.priv.partscorecount;

import com.priv.partflow.Flow;
import com.priv.partflow.FlowMapper;
import com.priv.partflow.FlowReducer;
import com.priv.partflow.PartFlowPartitioner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 统计每个人使用的总流量
 */
public class PartScoreDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取环境参数
        Configuration conf = new Configuration();
        //向Yarn申请任务
        Job job = Job.getInstance(conf);

        //指定Mapper类
        job.setMapperClass(PartScoreCountMapper.class);
        //指定Reducer类
        job.setReducerClass(PartScoreReducer.class);
        //指定Driver类
        job.setJarByClass(PartScoreDriver.class);


        //！！！！！！！！设置分区类
        job.setPartitionerClass(PartScorePartitioner.class);
        //设置ReduceTask。一个分区对应一个
        job.setNumReduceTasks(3);

        //指定Mapper输出结果的KV类型
        //注意：！！！！如果Mapper和Reduce的类型一样，那么可以不写
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(PartStudent.class);

        //指定Reduce输出结果的KV类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        //指定要处理的文件路径
        FileInputFormat.addInputPath(job, new Path("hdfs://10.211.55.5:9000/video2/txt/score1"));
        //指定结果的输出路径，要求这个路径不存在
        FileOutputFormat.setOutputPath(job, new Path("hdfs://10.211.55.5:9000/result/part/scorecount1"));
        //交给yarn去执行，直到执行结束才退出本程序
        job.waitForCompletion(true);
    }
}
