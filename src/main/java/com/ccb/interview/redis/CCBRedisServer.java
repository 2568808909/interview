package com.ccb.interview.redis;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一个假的RedisServer 用于抓包
 */
public class CCBRedisServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6379);
        Socket res = serverSocket.accept();
        InputStream inputStream = res.getInputStream();
        try {
            while (true) {
                byte[] data = new byte[2048];
                inputStream.read(data);
                System.out.println(new String(data));
            }
        } catch (Exception e) {

        } finally {
            inputStream.close();
            res.close();
        }
    }
}
