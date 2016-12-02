package com.example.lawson.androidsummery.edittext;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lawson.androidsummery.R;

public class EditTextActivity extends AppCompatActivity {

    private TextView text;
    private EditText edit_text;
    private EditText edit_text_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        text = (TextView) findViewById(R.id.text);
        edit_text = (EditText) findViewById(R.id.edit_text);
        edit_text_2 = (EditText) findViewById(R.id.edit_text_2);

//        edit_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "yoyo", Toast.LENGTH_SHORT).show();
//            }
//        });

        edit_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Toast.makeText(getBaseContext(), "yoyo", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "shit", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edit_text_2.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                if (str.length() > 10) {
                    edit_text_2.setText(str.substring(0, 10));
                }
                updateInputNumberState(edit_text_2.getText().length());
                Selection.setSelection(edit_text_2.getText(), edit_text_2.getText().length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edit_text_2.setText("1");
//        edit_text.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Toast.makeText(getBaseContext(), "yoyo", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

    }

    private void updateInputNumberState(int number) {
        String format = getResources().getString(R.string.experiences_of_answer_max_number);
        String result = String.format(format, number);
        int start = result.indexOf(",") + 1;
        int end = result.indexOf("/");
        SpannableString spannableString = new SpannableString(result);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#FF00ba9e"));
        spannableString.setSpan(colorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(spannableString);
    }

}
