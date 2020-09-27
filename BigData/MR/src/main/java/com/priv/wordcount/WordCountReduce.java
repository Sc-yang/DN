package com.priv.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


/**
 * MAPPER输出到Reducer
 * Reducer的4个泛型：
 * KEYIN - 必须和Mapper的输出类型（KEYOUT）一致 - Text
 * VALUEIN - 和Mapper的输出类型(VALUEOUT)一致 - LongWritable
 * KEYOUT - 输出类型 - Text
 * VALUEOUT - 输出的值的类型 - LongWritable
 *
 */

public class WordCountReduce extends Reducer<Text, LongWritable, Text, LongWritable> {

    /**
     * 将相同的键所对应的值放入一个迭代器中传递到reduce方法中
     * hello 1 1 1 1 1
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

        long sum = 0L;
        for (LongWritable val : values){
            sum += val.get();
        }
        context.write(key, new LongWritable(sum));
    }
}
