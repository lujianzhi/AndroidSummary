package com.example.lawson.androidsummery.databinding;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.databinding.been.ObservableStudent;
import com.example.lawson.androidsummery.databinding.been.Student;
import com.example.lawson.androidsummery.databindingbeen.ActivityDataBindingHelper;

import androidx.databinding.DataBindingUtil;

public class DataBindingActivity extends AppCompatActivity implements View.OnClickListener {

    private ObservableStudent observableStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不需要Activity的setContentView了
//        setTvContentView(R.layout.activity_data_binding);
        //ActivityDataBindingHelper这个辅助类就是在步骤二中data class中声明定义的辅助类
        ActivityDataBindingHelper bindingHelper = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        Student student = new Student("Ian", 24);
        bindingHelper.setStudent(student);

        observableStudent = new ObservableStudent("Ian", 24);
        bindingHelper.setObservableStudent(observableStudent);

        bindingHelper.bindingButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.binding_button:
                Toast.makeText(getBaseContext(), "name : " + observableStudent.getName().get(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
