package com.example.lawson.androidsummery.ipc.been;

import java.io.Serializable;

/**
 * Created by Ian.Lu on 2017/8/30.
 * Project : AndroidSummary
 */

public class MySerializable implements Serializable {

    private static final long serialVersionUID = 1L;

    private int age;
    private String name;

    public MySerializable(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MySerializable{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
