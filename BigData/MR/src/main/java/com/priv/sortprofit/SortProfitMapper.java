package com.priv.sortprofit;

import com.priv.partprofit.ProfitUser;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SortProfitMapper extends Mapper<LongWritable, Text, SortProfitUser, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] strings = value.toString().split(" ");
        SortProfitUser sortProfitUser = new SortProfitUser();

        sortProfitUser.setMonth(Integer.parseInt(strings[0]));
        sortProfitUser.setName(strings[1]);
        sortProfitUser.setProfile(Integer.parseInt(strings[2]));

        context.write(sortProfitUser, NullWritable.get());

    }
}
