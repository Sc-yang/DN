package com.jt.test.redis;

import org.junit.Test;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试Jedis代码客户端操作
 * 单个节点和多个几点的代码api
 */
public class JedisTest {

    //案例1，获取简单的Jedis对象连接redis服务
    @Test
    public void test01(){
        Jedis jedis = new Jedis("10.35.6.147",6379);
        jedis.set("class","1812");
        jedis.hset("user","age","18");
        jedis.lpush("list01","one","two","three","four","five");

        jedis.close();
    }


    //案例2，hash取余计算分片结果
    @Test
    public void test02(){
        Jedis jedis1 = new Jedis("10.35.6.147",6379);
        Jedis jedis2 = new Jedis("10.35.6.147",6380);
        Jedis jedis3 = new Jedis("10.35.6.147",6381);

        for(int i = 0; i < 100; i++){
            String key = "product_" + i;
            String productValue = "product_values_" + i;
            // &&Integer.MAX_VALUE 后得到该值的绝对值，保证hashcode值为正整数
            // 位的与运算比取绝对值快
            int result = key.hashCode()&Integer.MAX_VALUE;
            int nodeNNum = result % 3;

            switch (nodeNNum){
                case 0:
                    jedis1.set(key,productValue);
                    break;
                case 1:
                    jedis2.set(key,productValue);
                    break;
                case 2:
                    jedis3.set(key,productValue);
            }
        }
    }

    //案例3,jedis封装好的hash一致性算法使用的分片对象，进行分片的切分处理
    @Test
    public void test03(){

        List<JedisShardInfo> list = new ArrayList<>();
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("10.35.6.147",6379);
        JedisShardInfo jedisShardInfo2 = new JedisShardInfo("10.35.6.147",6380);
        JedisShardInfo jedisShardInfo3 = new JedisShardInfo("10.35.6.147",6381);

        list.add(jedisShardInfo1);
        list.add(jedisShardInfo2);
        list.add(jedisShardInfo3);

        ShardedJedis sJedis = new ShardedJedis(list);

        for(int i = 0; i < 100; i++){
            String key = "key_" + i;
            String value = "value_" + i;
            sJedis.set(key, value);
        }

        if(sJedis.exists("key_1")){
            System.out.println(sJedis.get("key_1"));
        }
    }

    //案例4 Jedis分片连接池
    @Test
    public void test04(){
        List<JedisShardInfo> list = new ArrayList<>();
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("10.35.6.147",6379);
        JedisShardInfo jedisShardInfo2 = new JedisShardInfo("10.35.6.147",6380);
        JedisShardInfo jedisShardInfo3 = new JedisShardInfo("10.35.6.147",6381);

        list.add(jedisShardInfo1);
        list.add(jedisShardInfo2);
        list.add(jedisShardInfo3);

        //生成连接池配置对象config
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(200);// 最大连接数
        jedisPoolConfig.setMaxIdle(10);// 最大空闲连接

        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, list);

        ShardedJedis sJedis = shardedJedisPool.getResource();

        for(int i = 0; i < 100; i++){
            String key = "key_" + i;
            String value = "value_" + i;
            sJedis.set(key, value);
        }

        if(sJedis.exists("key_1")){
            System.out.println(sJedis.get("key_1"));
        }
    }
}
