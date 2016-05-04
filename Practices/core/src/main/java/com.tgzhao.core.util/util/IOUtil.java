package com.tgzhao.core.util.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tgzhao on 2016/5/4.
 */
public abstract class IOUtil {

    public static void copy(InputStream inputStream, String filePath) throws IOException {
        copy(inputStream, new File(filePath));
    }

    public static void copy(InputStream inputStream, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(IOUtil.readBytes(inputStream));
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public static String read(InputStream inputStream) throws IOException {
        return new String(readBytes(inputStream), "UTF-8");
    }

    public static byte[] readBytes(InputStream inputStream) throws IOException {
        byte[] stringBytes = new byte[0];
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes)) > 0) {
            byte[] tempStringBytes = new byte[stringBytes.length + len];
            System.arraycopy(stringBytes, 0, tempStringBytes, 0, stringBytes.length);
            System.arraycopy(bytes, 0, tempStringBytes, stringBytes.length, len);
            stringBytes = tempStringBytes;
        }

        return stringBytes;
    }
}
