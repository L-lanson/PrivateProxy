package com.lenson.test;

import com.lenson.proxy.Printer;
import com.lenson.proxy.PrinterHandler;
import com.lenson.util.ByteCodeGenerator;
import com.lenson.util.ClassGenerator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;

/**
 * 测试类
 */
public class Test {
    public static void main(String[] args) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        //设置环境变量，将生成的class文件写入磁盘
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        //通过调用ByteCodeGenerator的generateProxyClass方法，生成代理类的byte数组
        byte[] bytes = ByteCodeGenerator.generateProxyClass("MyProxy",
                Printer.class, Printer.class.getInterfaces());
        //调用ClassGenerator的generate方法将代理类的byte数组生成Class对象
        Class<?> cls = ClassGenerator.generate(bytes);

        //新建被代理的对象和handler
        Printer p = new Printer();
        PrinterHandler handler = new PrinterHandler(p);

        //将handler作为参数，传入代理类的构造方法
        Constructor<?> constructor = cls.getConstructor(InvocationHandler.class);
        Printer p1 = (Printer) constructor.newInstance(handler);
        //通过代理对象执行目标方法
        p1.print();
    }
}
