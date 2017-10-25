package com.example.lawson.androidsummery.greendao.been;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Ian.Lu on 2017/10/19.
 * Project : AndroidSummary
 */

@Entity
public class Animal {
    @Id(autoincrement = true)
    private long id;
    private String name;
    private String kind;
    private int age;
    @Generated(hash = 1656765431)
    public Animal(long id, String name, String kind, int age) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.age = age;
    }
    @Generated(hash = 308569294)
    public Animal() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getKind() {
        return this.kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
