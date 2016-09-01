package com.example.lawson.androidsummery.javatest;

/***
 * Created by Lawson on 2016/8/31.
 */
public class Sample {     //运行时, jvm 把appmain的信息都放入方法区
    /**
     * 范例名称
     */
    private String name;      //new Sample实例后， name 引用放入栈区里，  name 对象放入堆里

    /**
     * 构造方法
     */
    public Sample(String name) {
        this.name = name;
    }

    /**
     * 输出
     */
    public void printName()   //print方法本身放入 方法区里。
    {
        System.out.println(name);
    }
}
