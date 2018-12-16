package com.example.lawson.androidsummery.popupwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lawson.androidsummery.R;

public class PopupWindowActivity extends AppCompatActivity {

    private Button show;
    private TestPopupWindow testPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);

        show = findViewById(R.id.show);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });

    }

    private void showPopupWindow(){
        testPopupWindow = new TestPopupWindow(this);
        testPopupWindow.showAsDropDown(show);
    }

}
