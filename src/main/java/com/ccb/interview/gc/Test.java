package com.ccb.interview.gc;


import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Test {

    public static void main(String[] args) throws IOException {
        Map<String, String> header = new HashMap<String, String>();
        header.put(
                "Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Accept-Language", "zh-CN,zh;q=0.9");
        header.put("Cache-Control", "max-age=0");
        header.put("Connection", "close");
        header.put(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64;X11; Linux 64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
        String common="http://up2.uppdir.com/file/2020/01/14/2233";
//        for(int i=1000;i<10000;i++) {
            //String url=common+i+".jpg";
            Connection connect = Jsoup.connect("https://pics.javcdn.pw/thumb/7jz8.jpg").ignoreContentType(true);
            connect.headers(header);
            Connection.Response response = connect.execute();
        System.out.println(response.url());
//            if(response.url().toString().equals(url)){
//                System.out.println(url);
//            }
//        }
    }
}
