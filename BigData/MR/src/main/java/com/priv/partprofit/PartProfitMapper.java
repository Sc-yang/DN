package com.priv.partprofit;

import com.priv.partscorecount.PartStudent;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.io.StringReader;

public class PartProfitMapper extends Mapper<LongWritable, Text, IntWritable, ProfitUser> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] strings = value.toString().split(" ");
        ProfitUser profitUser = new ProfitUser();
        profitUser.setMonth(Integer.parseInt(strings[0]));
        profitUser.setName(strings[1]);
        profitUser.setIncome(Integer.parseInt(strings[2]));
        profitUser.setExpend(Integer.parseInt(strings[3]));

        context.write(new IntWritable(profitUser.getMonth()), profitUser);

    }
}
