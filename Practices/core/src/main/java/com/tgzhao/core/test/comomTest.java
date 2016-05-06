package com.tgzhao.core.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tgzhao on 2016/4/23.
 */
public class comomTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        Thread.sleep(100);
        System.out.println(System.currentTimeMillis());

        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis() - 1000));
        System.out.println(startTime);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(startTime);
    }
}
