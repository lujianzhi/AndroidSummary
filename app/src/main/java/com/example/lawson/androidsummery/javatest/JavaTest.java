package com.example.lawson.androidsummery.javatest;

/***
 * Created by Lawson on 2016/8/31.
 */
public class JavaTest {

    public static void main(String[] str) {
//        Person p1 = new Person();
//        Person p2 = new Person(1);

        test();

        System.out.println("main");

    }

    private static void test(){
        for(int i=0;i<10;i++){
            if(i==7){
                //直接跳出test()方法
                return;
            }
            System.out.println("我是"+i);
        }

        System.out.println("return test");
    }

    static class Person {
        //构造代码块
        {
            System.out.println("我是构造代码块");
        }
        //静态构造代码块
        static{
            System.out.println("我是静态构造代码块");
        }

        public Person(int i){
            System.out.println("我是有参构造方法");
        }

        public Person(){
            System.out.println("我是无参构造方法");
        }

    }
}
