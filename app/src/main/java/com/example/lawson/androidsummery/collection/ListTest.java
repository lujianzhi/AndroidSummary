package com.example.lawson.androidsummery.collection;

import com.example.lawson.androidsummery.detectmemory.entity.ObjWithoutContext;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {

    private static List<ObjWithoutContext> arrayList = new ArrayList<ObjWithoutContext>();
    private static List<ObjWithoutContext> linkedList = new LinkedList<>();

    public static void main(String[] args) {

        for (long i = 0; i < 9999999; i++) {
//            arrayList.add(new ObjWithoutContext("content-" + i));
            linkedList.add(new ObjWithoutContext("content-" + i));
        }

        //使用ArrayList查找  时间：125
//        indexArrayList(arrayList);
        //使用ArrayList插入  时间:7
//        insertArrayList(arrayList, 1, new ObjWithoutContext("new add"));

        //LinkedList  时间:263
//        indexLinkedList(linkedList);
        //使用ArrayList插入  时间:0
        insertLinkedList(linkedList, 1, new ObjWithoutContext("new add"));
    }

    private static void insertLinkedList(List<ObjWithoutContext> linkedList, int index, ObjWithoutContext obj) {
        long start = System.currentTimeMillis();
        linkedList.add(index, obj);
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.print("插入时间 : " + duration);
    }

    private static void indexLinkedList(List<ObjWithoutContext> linkedList) {
        long start = System.currentTimeMillis();
        int index = linkedList.indexOf(new ObjWithoutContext("content-9999998"));
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.print("位于 : " + index + " ; 时间 : " + duration);
    }

    private static void insertArrayList(List<ObjWithoutContext> arrayList, int index, ObjWithoutContext obj) {
        long start = System.currentTimeMillis();
        arrayList.add(index, obj);
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.print("插入时间 : " + duration);
    }

    private static void indexArrayList(List<ObjWithoutContext> arrayList) {
        long start = System.currentTimeMillis();
        int index = arrayList.indexOf(new ObjWithoutContext("content-9999998"));
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.print("位于 : " + index + " ; 时间 : " + duration);
    }

}
