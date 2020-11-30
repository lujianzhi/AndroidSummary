package com.example.lawson.androidsummery.junittest;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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

        showTv = findViewById(R.id.show_text_view);
        inputEt = findViewById(R.id.input_edit_text);
        saveBtn = findViewById(R.id.save_button);
        clearBtn = findViewById(R.id.clear_button);

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
