package com.example.lawson.androidsummery.greendao.been;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * Created by Ian.Lu on 2017/10/18.
 * Project : AndroidSummary
 */

@Entity(
        nameInDb = "IanUser"
)
public class User {
    @Id(autoincrement = true)
    private Long userId;

    private String name;

    private int age;

    @Transient
    private List<String> cards;

    @Generated(hash = 666819091)
    public User(Long userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}' + "\n";
    }
}
