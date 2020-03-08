package com.ccb.interview.map;

import java.util.HashMap;

/**
 * 证明HashMap扩容不会进行Rehash，在ReHash代码处打一断点，使用debug模式运行
 */
public class TestHashMap {


    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            String s = String.valueOf(i);
            map.put(s, s);
        }
    }

}
