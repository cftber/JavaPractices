package com.tgzhao.core.util;

import com.tgzhao.core.util.com.tgzhao.core.redis.RedisClient;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by tgzhao on 2016/4/2.
 */
public class SpringApplicationContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;
    private static String REGISTER_REDIS_CLIENT_KEY = "redisClient";

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(String name) {
        Object object = null;
        if (applicationContext != null) {
            object =applicationContext.getBean(name);
        }
        return (T) object;
    }

    public static RedisClient getMemcachedClient() {
        RedisClient mc = getBean(REGISTER_REDIS_CLIENT_KEY);
        return mc;
    }
}
