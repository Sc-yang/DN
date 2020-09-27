package com.priv.join;

import com.sun.tools.corba.se.idl.constExpr.Or;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JoinReducer extends Reducer<Text, Order, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Order> values, Context context) throws IOException, InterruptedException {

        int total = 0;
        String name = "";

        for(Order order : values){
            total = order.getNum()*order.getPrice();
            name = order.getName();
        }

        context.write(key, new Text(name + "\t" + total));
    }


}
