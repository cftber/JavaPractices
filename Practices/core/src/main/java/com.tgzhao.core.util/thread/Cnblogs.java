package com.tgzhao.core.util.thread;

import com.tgzhao.core.util.util.IOUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tgzhao on 2016/5/4.
 */
public class Cnblogs {
    public static void fetchArticlesCommon() throws IOException {
        for (int i = 0; ; i ++) {
            Document document = Jsoup.connect("http://www.cnblogs.com/zuoxiaolong/p/niubi-job-5.html").get();
            Element mainElement = document.getElementById("mainContent");
            Elements elements = mainElement.getElementsByClass("postTitle");

            String basePath = "D:\\ImgDir\\";
            Elements imgElements = document.getElementsByTag("img");
            for (Element imgElement : imgElements) {
                System.out.println(imgElement.toString());
                String imgUrl = imgElement.attr("src");
                String suffix = imgUrl.substring(imgUrl.lastIndexOf('.'), imgUrl.length());
                if (suffix.length() > 4) {
                    suffix = ".jpg";
                }
                String path = basePath + new SimpleDateFormat("ddHHmmSSS").format(new Date()) + suffix;

                File file = new File(path);
                if (!file.exists()) {
                    HttpURLConnection connection = (HttpURLConnection) new URL(imgUrl).openConnection();
                    connection.setRequestMethod("GET");
                    IOUtil.copy(connection.getInputStream(), file);
                }
            }
        }
    }
}
