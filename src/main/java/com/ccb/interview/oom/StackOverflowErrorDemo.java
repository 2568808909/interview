package com.ccb.interview.oom;

/**
 *  StackOverflowError: 栈溢出，常见于递归调用无出口，或方法调用过多吧栈空间撑爆了
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();
    }
}
