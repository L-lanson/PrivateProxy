package com.lenson.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PrinterHandler implements InvocationHandler {

    private IPrinter printer;

    public PrinterHandler(IPrinter printer){
        this.printer = printer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(printer, args);
        return null;
    }
}
