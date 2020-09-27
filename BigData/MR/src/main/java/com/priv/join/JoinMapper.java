package com.priv.join;

import com.sun.tools.corba.se.idl.constExpr.Or;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class JoinMapper extends Mapper<LongWritable, Text, Text, Order> {

    private Map<String, Order> map;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //从内存中获取小文件
        URI file = context.getCacheFiles()[0];
        //连接HDFS
        FileSystem fs = FileSystem.get(file, context.getConfiguration());
        //获取输入流读取小文件
        InputStream in = fs.open(new Path(file));
        //转字符流使用
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));

        map = new HashMap<>();

        //读取内容
        String line = null;
        while ((line = bf.readLine()) != null){
            String[] arr = line.split(" ");
            Order order = new Order();
            order.setProid(arr[0]);
            order.setName(arr[1]);
            order.setPrice(Integer.parseInt(arr[2]));
            map.put(order.getProid(), order);
        }
        bf.close();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] arr = value.toString().split(" ");
        Order order = new Order();
        order.setOrderid(arr[0]);
        order.setDate(arr[1]);
        order.setProid(arr[2]);
        order.setNum(Integer.parseInt(arr[3]));
        order.setName(map.get(order.getProid()).getName());
        order.setPrice(map.get(order.getProid()).getPrice());

        context.write(new Text(order.getOrderid()), order);

    }
}
