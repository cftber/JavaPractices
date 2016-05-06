package com.tgzhao.core.util.thread;

/**
 * Created by tgzhao on 2016/5/4.
 */
public abstract class Executor {
    public static void executeTash(Runnable task) {
        Thread thread = new Thread(task);
        thread.start();
    }
}
