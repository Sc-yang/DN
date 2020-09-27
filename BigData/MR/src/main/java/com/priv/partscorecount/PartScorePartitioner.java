package com.priv.partscorecount;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.log4j.Logger;

/**
 * Partitioner的泛型是Mapper的输出类型
 */
public class PartScorePartitioner extends Partitioner<Text, PartStudent> {
    private Logger LOGGER = Logger.getLogger(PartScorePartitioner.class);

    // 参数为：数据的键、数据的值、分区数量
    @Override
    public int getPartition(Text key, PartStudent value, int numReduceTask) {

        int month = value.getMonth();

        if(month == 1){
            return 0;
        }else if(month == 2){
            return 1;
        }else {
            return 2;
        }

    }
}
