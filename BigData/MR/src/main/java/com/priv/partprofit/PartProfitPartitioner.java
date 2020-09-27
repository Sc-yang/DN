package com.priv.partprofit;

import com.priv.partscorecount.PartStudent;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.log4j.Logger;

/**
 * Partitioner的泛型是Mapper的输出类型
 */
public class PartProfitPartitioner extends Partitioner<IntWritable, ProfitUser> {
    private Logger LOGGER = Logger.getLogger(PartProfitPartitioner.class);

    // 参数为：数据的键、数据的值、分区数量
    @Override
    public int getPartition(IntWritable key, ProfitUser value, int numReduceTask) {

        String name = value.getName();

        if (name.equals("ls")) {
            return 0;
        } else if (name.equals("zs")) {
            return 1;
        } else {
            return 2;
        }

    }
}
