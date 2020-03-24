package com.ccb.interview.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Example {

    private Jedis jedis = new Jedis("hadoop102", 6379);

    @Test
    public void RESP() {
        /**
         * Redis RESP协议（REdis Serialization Protocol） 是Redis的序列化协议
         * *3               代表有三组指令
         * $3               代表这条指令长度为3
         * SET              指令内容
         * $3               代表这条指令长度为3
         * ccb
         * $11              代表这条指令长度为11
         * 15545964816
         */

        jedis.set("ccb", "15545964816");
    }

    @Test
    public void lockUseLua() {
        String script = "if redis.call(\"setnx\",KEYS[1],ARGV[1]) == 1 then\n" +
                "    return redis.call(\"expire\",KEYS[1],60)\n" +
                "else\n" +
                "    return 0;\n" +
                "end";
        System.out.println(jedis.eval(script, Collections.singletonList("lock"), Collections.singletonList("10")));
    }

    @Test
    public void unlockUseLua() {
        String script="if redis.call(\"get\",KEYS[1]) == ARGV[1] then\n" +
                "    return redis.call(\"del\",KEYS[1])\n" +
                "else\n" +
                "    return 0\n" +
                "end";
        System.out.println(jedis.eval(script, Collections.singletonList("lock"), Collections.singletonList("10")));
       // System.out.println(jedis.eval(script, Collections.singletonList("lock"), Collections.singletonList("1")));
    }

}
