package com.ccb.interview.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * OutOfMemoryError: Metaspace 元空间溢出
 *
 * 加载的类信息过多，超过元空间上限
 * Java8后使用元空间替代永久代，Metaspace与永久代不同的是，其并不存储在虚拟机内存中，而是使用本地内存
 */
public class OOMMetaspace {

    static class OOMTest {
        public OOMTest(){}
    }

    public static void main(String[] args) {
        int i = 0;
        while (true) {
            i++;
            try {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }catch (Throwable e){
                System.out.println("*****************"+i);
                throw e;
            }
        }
    }
}
