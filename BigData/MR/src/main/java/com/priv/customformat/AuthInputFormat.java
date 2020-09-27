package com.priv.customformat;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.util.LineReader;

import java.io.IOException;
import java.net.URI;

public class AuthInputFormat extends FileInputFormat {
    @Override
    public RecordReader createRecordReader(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        return new AuthRecordReader();
    }
}

class AuthRecordReader extends RecordReader {

    private LineReader reader;
    private Text key;
    private Text value;

    //初始化
    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {

        //转化为文件切片，获取切片路径
        FileSplit fsplit = (FileSplit) inputSplit;
        Path path = fsplit.getPath();

        //连接HDFS
        FileSystem fs = FileSystem.get(URI.create(path.toString()), taskAttemptContext.getConfiguration());

        //获取输入流
        FSDataInputStream in = fs.open(path);
        //将字节流包装成字符流，方便按行读取
        reader = new LineReader(in);
    }

    //每次执行map方法之前，调用此方案查看是否有下一个键值对
    //返回true则还有数据，返回false则没有数据了此次MapTask应该结束了
    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        //定义变量存储键值
        key = new Text();
        value = new Text();
        //定义一个临时变量
        Text tmp = new Text();

        //读取数据
        //返回值表示读取到的字节个数，读到末尾则为0
        if (reader.readLine(tmp) == 0) {
            return false;
        }
        key.set(tmp);

        if (reader.readLine(tmp) == 0) {
            return false;
        }
        value.set(tmp);
        value.append(new Text(" ").getBytes(), 0, 1);

        if (reader.readLine(tmp) == 0) {
            return false;
        }
        value.append(tmp.getBytes(), 0, tmp.getLength());

        return true;
    }

    //获取键
    @Override
    public Object getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    //获取值
    @Override
    public Object getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    //获取文件处理进度
    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public void close() throws IOException {
        if(reader != null){
            reader.close();
    }
}

}
