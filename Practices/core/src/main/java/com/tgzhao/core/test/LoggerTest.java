package com.tgzhao.core.test;

import org.apache.log4j.Logger;

/**
 * Created by tgzhao on 2016/5/8.
 * log4j demo
 */
public class LoggerTest {
    static final Logger logger = Logger.getLogger(LoggerTest.class);
    public static void main(String[] args) {
        logger.info("info message");
        logger.trace("trace msg");
        logger.debug("debug msg");
        logger.warn("warn msg");
        logger.error("error msg");
    }
}
