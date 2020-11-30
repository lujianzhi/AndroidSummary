package com.example.lawson.androidsummery.databinding.been;


import androidx.databinding.ObservableField;

/**
 * Created by Ian.Lu on 2017/8/17.
 * Project : AndroidSummary
 */

public class ObservableStudent {

    //使用ObservableField来包装name以及age
    private ObservableField<String> name = new ObservableField<>();
    private ObservableField<Integer> age = new ObservableField<>();

    public ObservableStudent(String name, int age) {
        this.name.set(name);
        this.age.set(age);
    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }

    public ObservableField<Integer> getAge() {
        return age;
    }

    public void setAge(ObservableField<Integer> age) {
        this.age = age;
    }
}
