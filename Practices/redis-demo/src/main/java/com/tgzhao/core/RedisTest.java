package com.tgzhao.core;

import com.tgzhao.core.util.com.tgzhao.core.redis.RedisFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.ShardedJedis;

/**
 * Created by tgzhao on 2016/4/2.
 */
public class RedisTest {
    public static void main(String[] args) {
        ApplicationContext factory=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        String str = RedisFactory.doWithShardedJedis("demo", new RedisFactory.ShardedJedisWorker<String>(){
            @Override
            public String work(ShardedJedis jedis) {
                jedis.set("redisdemo", "test data hahaha hahah ");
                String strFromRedis = jedis.get("redisdemo");
                System.out.println(jedis.get("redisdemo"));
                return strFromRedis;
            }
        });
        System.out.println("str is: " + str);
    }
}
