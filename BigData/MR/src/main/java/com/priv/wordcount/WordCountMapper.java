package com.priv.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


/**
 * MapReduce中要求所传输的数据必须能够序列化
 * MapReduce采取的序列化机制默认是AVRO
 * MAPPER的4个泛型：
 * KEYIN 默认表示数据偏移量 长整型
 * VALUEIN - 表示输入的一行数据 - 字符串
 * KEYOUT - 输出的键的类型
 * VALUEOUT - 输出的值的类型
 *
 */
public class WordCountMapper extends Mapper<LongWritable ,Text ,Text , LongWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //数据是一行一行的传入，value.toString()代表着一行，每行数据运行一次map
        String[] arr = value.toString().split(" ");

        for (String str : arr){
            // context 是 Hadoop中的上下文，hadoop计算结果用KV的形式存如下
            //hello 1
            //sunchenyang 1
            //hello 1
            //world 1
            context.write(new Text(str), new LongWritable(1));
        }
    }
}
