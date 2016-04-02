package com.tgzhao.core.util.com.tgzhao.core.redis;

import com.tgzhao.core.util.SpringApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Created by tgzhao on 2016/4/2.
 */
public class RedisFactory {
    private static final String SHARDED_JEDIS_POOL_NAME_PREFIX = "shardedJedisPool_";

    private static final String JEDIS_POOL_NAME_PREFIX = "jedisPool_";

    /**
     * 获取一个可分布式的Jedis连接池
     * @param name
     * @return
     */
    public static ShardedJedisPool getShardedJedisPool(String name) {
        return SpringApplicationContext.getBean(SHARDED_JEDIS_POOL_NAME_PREFIX + name);
    }

    /**
     * 获取一个Jedis连接池
     * @param name
     * @return
     */
    public static JedisPool getJedisPool(String name) {
        return SpringApplicationContext.getBean(JEDIS_POOL_NAME_PREFIX + name);
    }

    /**
     * 通过一个可分布式的Jedis连接池进行操作
     * @param poolName
     * @param worker
     * @param <T>
     * @return
     */
    public static <T> T doWithShardedJedis(String poolName, ShardedJedisWorker<T> worker) {
        ShardedJedisPool pool = null;
        ShardedJedis jedis = null;
        boolean connectSuccess = true;
        try {
            pool = RedisFactory.getShardedJedisPool(poolName);
            jedis = pool.getResource();
            if (worker != null) {
                return worker.work(jedis);
            }
        } catch (JedisConnectionException e) {
            connectSuccess = false;
            System.out.print("work with sharded jedis exception. {}");
//            LogUtil._().warn("work with sharded jedis exception. {}", e.getMessage());
            if (jedis != null) {
                try {
                    pool.returnBrokenResource(jedis);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.print("work with sharded jedis exception.");
//            LogUtil._().warn("work with sharded jedis exception.", e);
        } finally {
            if (pool  != null && jedis != null && connectSuccess) {
                try {
                    pool.returnResource(jedis);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static interface ShardedJedisWorker<T> {
        T work(ShardedJedis jedis);
    }

    /**
     * 通过一个Jedis连接池进行操作
     * @param poolName
     * @param worker
     * @param <T>
     * @return
     */
    public static <T> T doWithJedis(String poolName, JedisWorker<T> worker) {
        JedisPool pool = null;
        Jedis jedis = null;
        boolean connectSuccess = true;
        try {
            pool = RedisFactory.getJedisPool(poolName);
            jedis = pool.getResource();
            if (worker != null) {
                return worker.work(jedis);
            }
        } catch (JedisConnectionException e) {
            connectSuccess = false;
            System.out.print("work with jedis exception. {}");
//            LogUtil._().warn("work with jedis exception. {}", e.getMessage());
            if (jedis != null) {
                try {
                    pool.returnBrokenResource(jedis);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }  catch (Exception e) {
            System.out.print("work with jedis exception.");
//            LogUtil._().warn("work with jedis exception.", e);
        } finally {
            if (pool  != null && jedis != null && connectSuccess) {
                try {
                    pool.returnResource(jedis);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static interface JedisWorker<T> {
        T work(Jedis jedis);
    }
}
