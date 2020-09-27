package com.priv.partflow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.log4j.Logger;

import java.awt.*;

/**
 * Partitioner的泛型是Mapper的输出类型
 */
public class PartFlowPartitioner extends Partitioner<Text, Flow> {
    private Logger LOGGER = Logger.getLogger(PartFlowPartitioner.class);

    // 参数为：数据的键、数据的值、分区数量
    @Override
    public int getPartition(Text key, Flow value, int numReduceTask) {

        String addr = value.getAddr();
        LOGGER.info("addr : " + addr);

        if(addr.equals("bj")){//bj 放到 0分区
            LOGGER.info("0000000000000000");
            return 0;
        }else if(addr.equals("sh")){//sh 放到 1分区
            LOGGER.info("1111111111111111");
            return 1;
        }else {//sz 放到 2分区
            LOGGER.info("2222222222222222");
            return 2;

        }

    }
}
