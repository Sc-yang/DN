package com.priv.partprofit;

import com.priv.partscorecount.PartStudent;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PartProfitReducer extends Reducer<IntWritable,ProfitUser,IntWritable, IntWritable> {
    @Override
    protected void reduce(IntWritable key, Iterable<ProfitUser> values, Context context) throws IOException, InterruptedException {

//        ProfitUser profitUser = values.iterator().next();
//        int profit =profitUser.getIncome() - profitUser.getExpend();

        int profit = 0;

        for (ProfitUser profitUser : values){
            profit += (profitUser.getIncome() - profitUser.getExpend());
        }

        context.write(key,new IntWritable(profit));
    }
}
