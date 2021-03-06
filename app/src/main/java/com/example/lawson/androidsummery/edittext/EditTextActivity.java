package com.example.lawson.androidsummery.edittext;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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
    private EditText edit_text_1;
    private EditText edit_text_2;
    private EditText edit_text_3;
    private EditText edit_text_4;
    private EditText edit_text_5;
    private EditText edit_text_6;
    private EditText edit_text_7;
    private EditText edit_text_8;
    private EditText edit_text_9;
    private EditText edit_text_10;
    private EditText edit_text_11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        text = findViewById(R.id.text);
        edit_text_1 = findViewById(R.id.edit_text_1);
        edit_text_2 = findViewById(R.id.edit_text_2);
        edit_text_3 = findViewById(R.id.edit_text_3);
        edit_text_4 = findViewById(R.id.edit_text_4);
        edit_text_5 = findViewById(R.id.edit_text_5);
        edit_text_6 = findViewById(R.id.edit_text_6);
        edit_text_7 = findViewById(R.id.edit_text_7);
        edit_text_8 = findViewById(R.id.edit_text_8);
        edit_text_9 = findViewById(R.id.edit_text_9);
        edit_text_9.setText("\uD83D\uDE2F");
        edit_text_10 = findViewById(R.id.edit_text_10);
        edit_text_11 = findViewById(R.id.edit_text_11);

//        edit_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "yoyo", Toast.LENGTH_SHORT).show();
//            }
//        });

        edit_text_9.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Toast.makeText(getBaseContext(), "yoyo", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "shit", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edit_text_1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String wordsStr = s.toString();
                if (wordsStr.length() >= 2) {
                    CharSequence charSequence = s.subSequence(s.toString().length() - 2, s.toString().length());
                    if (containsEmoji(charSequence.toString())) {
                        Toast.makeText(EditTextActivity.this, "不支持表情", Toast.LENGTH_SHORT).show();
                        if (wordsStr.length() > 120) {
                            edit_text_1.setText(wordsStr.substring(0, 120));
                        } else {
                            edit_text_1.setText(wordsStr.substring(0, s.toString().length() - 1));
                        }
                        return;
                    }
                }
                if (wordsStr.length() > 120) {
                    edit_text_1.setText(wordsStr.substring(0, 120));
                }
                updateInputNumberState(edit_text_1.getText().length());
                Selection.setSelection(edit_text_1.getText(), edit_text_1.getText().length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        edit_text.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Toast.makeText(getBaseContext(), "yoyo", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

        edit_text_2.setFocusable(true);
        edit_text_3.setFocusable(true);
        edit_text_4.setFocusable(true);

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

    /**
     * 检测是否有emoji表情
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }

}
