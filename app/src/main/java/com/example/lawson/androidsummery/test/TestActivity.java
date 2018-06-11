package com.example.lawson.androidsummery.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.*;
import com.example.lawson.androidsummery.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Switch switchOne = (Switch) findViewById(R.id.switch_one);
        Switch switchTwo = (Switch) findViewById(R.id.switch_two);
        testSwitch(switchOne, switchTwo);

        EditText editText = (EditText) findViewById(R.id.edit_text);
        TextView tip = (TextView) findViewById(R.id.tip);
        testEditText(editText, tip);
    }

    private void testEditText(final EditText editText, final TextView textView) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = editText.getText();
                int textLen = editable.length();
                Log.i("Ian", "editable : " + editable.toString() + " ; editable.length : " + textLen);
                if (textLen < 5) {
                    textView.setText("还需要输入" + (5 - textLen) + "个字");
                } else if (textLen <= 10) {
                    textView.setText(textLen + "/10");
                } else {
                    int selEndIndex = Selection.getSelectionEnd(editable);
                    String oriStr = editable.toString();
                    String newStr = oriStr.substring(0, textLen - count);
                    editText.setText(newStr);
                    editable = editText.getText();
                    int newLen = editable.length();
                    if (selEndIndex > newLen) {
                        selEndIndex = editable.length();
                    }
                    Selection.setSelection(editable, selEndIndex);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void testSwitch(final Switch switchOne, final Switch switchTwo) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int id = buttonView.getId();
                switch (id) {
                    case R.id.switch_one:
                        Toast.makeText(TestActivity.this, "switch_one", Toast.LENGTH_SHORT).show();
                        if (isChecked) {
                            switchTwo.setEnabled(false);
                        } else {
                            switchTwo.setEnabled(true);
                        }
                        break;
                    case R.id.switch_two:
                        Toast.makeText(TestActivity.this, "switch_two", Toast.LENGTH_SHORT).show();
                        if (isChecked) {
                            switchOne.setEnabled(false);
                        } else {
                            switchOne.setEnabled(true);
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        switchOne.setOnCheckedChangeListener(onCheckedChangeListener);
        switchTwo.setOnCheckedChangeListener(onCheckedChangeListener);
    }
}
