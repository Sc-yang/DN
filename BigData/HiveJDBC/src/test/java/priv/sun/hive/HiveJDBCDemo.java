package priv.sun.hive;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HiveJDBCDemo {


    @Test
    public void testConnectAndQuery() throws Exception {
        //注册数据库驱动，用的hive的jdbc，驱动名固定写死
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        //如果用的是hive2服务，则写jdbc:hive2，后面跟上hive服务器的ip以及端口号和hive库名，端口号默认是10000
        Connection conn = DriverManager.getConnection("jdbc:hive2://10.211.55.11:10000/mydatabase", "root", "root");
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("select * from map2");
        while (rs.next()) {
            String name = rs.getString("vals");
            System.out.println(name);
        }

        stat.close();
        conn.close();
    }
}
