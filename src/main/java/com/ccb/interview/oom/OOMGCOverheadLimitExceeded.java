package com.ccb.interview.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * OutOfMemoryError: GC overhead limit exceeded
 * GC回收时间过长，这个过长的定义是，使用了98%的时间来回收，但是回收了不到2%的空间，而且连续多次回收都是这个效果
 */
public class OOMGCOverheadLimitExceeded {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        int i = 0;
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("************************" + i);
            throw e;
        }
    }
}
