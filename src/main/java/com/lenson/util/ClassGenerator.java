package com.lenson.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射调用ClassLoader的defineClass方法，将byte[]转化成Class
 * @author lenson
 * @version 1.5
 */
public class ClassGenerator {
    public static Class<?> generate(byte[] bytes) throws NoSuchMethodException, IllegalAccessException
            , InvocationTargetException {
        //获取ClassLoader的Class对象
        Class<?> loaderClass = ClassGenerator.class.getClassLoader().getClass()
                .getSuperclass().getSuperclass().getSuperclass();

        //调用ClassLoader的defineClass方法，生成代理类的Class对象
        Method m = loaderClass.getDeclaredMethod("defineClass", byte[].class,
                int.class, int.class);
        m.setAccessible(true);
        Class<?> cls = (Class<?>) m.invoke( ClassLoader.getSystemClassLoader(),
                bytes, 0, bytes.length);

        return cls;
    }
}
