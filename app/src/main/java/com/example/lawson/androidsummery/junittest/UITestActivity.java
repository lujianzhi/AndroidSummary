package com.example.lawson.androidsummery.junittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lawson.androidsummery.R;

public class UITestActivity extends AppCompatActivity {

    private TextView showTv;
    private EditText inputEt;
    private Button saveBtn;
    private Button clearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uitest);

        showTv = (TextView) findViewById(R.id.show_text_view);
        inputEt = (EditText) findViewById(R.id.input_edit_text);
        saveBtn = (Button) findViewById(R.id.save_button);
        clearBtn = (Button) findViewById(R.id.clear_button);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }

    public void save() {
        showTv.setText(inputEt.getText().toString());
        Toast.makeText(getBaseContext(), inputEt.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public void clear() {
        showTv.setText("empty");
        inputEt.setText("");
    }
}
