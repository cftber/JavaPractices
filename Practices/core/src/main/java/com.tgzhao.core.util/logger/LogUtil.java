package com.tgzhao.core.util.logger;

import com.tgzhao.core.util.common.ReflectionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tgzhao on 2016/4/2.
 */
public class LogUtil {
    private static Map<String, Logger> loggerCache = new HashMap<String, Logger>();

    public static Logger getLogger(String className) {
        Logger logger = loggerCache.get(className);
        if(logger == null) {
            logger = LoggerFactory.getLogger(className);
            loggerCache.put(className, logger);
        }
        return logger;
    }

    public static Logger getLogger(Class<?> klazz) {
        return getLogger(klazz.getName());
    }

    public static Logger _() { return getLogger(ReflectionHelper.getCallerClass(1)); }

    public static Logger _(int offsetLevel) { return getLogger(ReflectionHelper.getCallerClass(offsetLevel)); }
}
