package com.priv.invert;

import com.sun.tools.classfile.Opcode;
import org.apache.hadoop.io.SetFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class InvertReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        Set<String> set = new HashSet<>();
        for (Text text : values){
            String str = text.toString();
            set.add(str);
        }

        context.write(key, new Text(set.toString()));
    }


}
