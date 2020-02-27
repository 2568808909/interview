package com.ccb.interview.oom;

import java.util.Random;

/**
 * OutOfMemoryError: Java heap space   创建的对象过多，堆空间不足
 */
public class OOMJavaHeapSpace {

    public static void main(String[] args) {
        byte[] bytes = new byte[30 * 1024 * 1024];
//        String str="ccb";
//        while(true){
//            str+= new Random(2222222).nextInt()+new Random(11111111).nextInt();
//            str.intern();
//        }
    }
}
