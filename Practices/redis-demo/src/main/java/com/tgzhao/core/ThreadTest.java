package com.tgzhao.core;

import com.tgzhao.core.util.thread.Cnblogs;

import java.io.IOException;

/**
 * Created by tgzhao on 2016/5/4.
 */
public class ThreadTest {
    public static void main(String[] args) throws IOException {
//        Cnblogs.fetchArticlesCommon();
        //String str=new String(.getBytes("������������"),"ISO-8859-1");
        String aaa = "编码格式测试";
        String bbb = new String(aaa.getBytes(), "utf-8");
        System.out.println("编码格式测试");
        System.out.println(aaa);
        System.out.println(bbb);
    }
}
