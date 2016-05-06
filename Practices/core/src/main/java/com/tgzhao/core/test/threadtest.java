package com.tgzhao.core.test;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by tgzhao on 2016/4/23.
 */
public class threadtest {
    public static void main(String[] args) throws InterruptedException {
        final HashMap<String, String> hashMap = new HashMap<String, String>(2);
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        public void run() {
                            hashMap.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        thread.start();
        thread.join();
    }
}
