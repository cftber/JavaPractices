package com.tgzhao.core.util.common;

/**
 * Created by tgzhao on 2016/4/2.
 *
 * 反射帮助类
 */
public class ReflectionHelper {

    private static CallerGetter cg = new CallerGetter();

    /**
     * 获取调用者类
     * @return
     */
    public static Class<?> getCallerClass() {
        return cg.getCallerClass(0);
    }

    public static Class<?> getCallerClass(int offsetLevel) {
        return cg.getCallerClass(offsetLevel);
    }

    public static String getCallerClass2(int offsetLevel) {
        StackTraceElement[] steArray = new Throwable().getStackTrace();
        int level = 2 + offsetLevel;
        if(level < 0) {
            level = 0;
        }
        if(level > steArray.length-1) {
            return null;
        }

        return steArray[level].getClassName();
    }

    public static String getCallerClass3(int offsetLevel) {
        //return sun.reflect.Reflection().getCallerClass(offsetLevel);
        return null;
    }

    private static class CallerGetter extends SecurityManager {
        public Class<?> getCallerClass(int offsetLevel) {
            int level = 2 + offsetLevel;
            Class<?>[] cs =  getClassContext();
            if(level < 0) {
                level = 0;
            }
            if(level > cs.length-1) {
                return null;
            }
            return cs[level];
        }
    }
}