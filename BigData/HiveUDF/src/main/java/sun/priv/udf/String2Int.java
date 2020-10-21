package sun.priv.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class String2Int extends UDF {

    public int evaluate(String str){
        String newStr = str.replaceAll("\\D","");
        return Integer.parseInt(newStr);
    }

}
