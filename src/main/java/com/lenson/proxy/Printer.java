package com.lenson.proxy;

public class Printer implements IPrinter {

    @Override
    public void print() {
        printPrivate("Hello World");
    }

    private void printPrivate(String s){
        System.out.println(s);
    }
}
