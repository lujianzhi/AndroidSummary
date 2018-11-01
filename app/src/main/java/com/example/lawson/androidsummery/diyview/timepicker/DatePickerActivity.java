package com.example.lawson.androidsummery.diyview.timepicker;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.lawson.androidsummery.R;
import java.lang.reflect.Field;

public class DatePickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        findViewById(R.id.time_picker_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void datePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        try {
            Field field = datePickerDialog.getDatePicker().getClass().getDeclaredField("mDaySpinner");
            if (field != null) {
                field.setAccessible(true);
                View view = (View) field.get(datePickerDialog);
                view.setVisibility(View.GONE);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        datePickerDialog.show();
    }
}
