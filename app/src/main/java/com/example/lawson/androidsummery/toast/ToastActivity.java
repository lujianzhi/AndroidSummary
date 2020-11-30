package com.example.lawson.androidsummery.toast;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lawson.androidsummery.R;

public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        toast();
    }

    private void toast(){
        Toast toast = new Toast(this);
        toast.setDuration(10000);
        TextView textView = new TextView(this);
        textView.setText("1423");
        toast.setView(textView);
        toast.show();
    }

}
