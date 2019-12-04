package com.example.lawson.androidsummery.test;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
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

        testSwitch();

        testEditText();

        testEditShapeRes();

        testPost();

        //        getBundle();

        testHtml();

        testScrollView();
    }

    private void testScrollView() {
        TextView tv = findViewById(R.id.tv);
//        tv.setMaxHeight(200);
        tv.setText("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16\n17\n18\n19\n20\n21\22\n23\n24\n25\n26");
    }

    private void testHtml() {
        TextView html_text = findViewById(R.id.html_text);
        String title = "<font size=\"3\" color=\"#999999\">距离结束仅剩</font><br>" +
                "<font size=\"50\" color=\"#333333\">2天21时52分44秒</font>";
        html_text.setText(Html.fromHtml(title, null, new SizeLabel(this, 30)));
    }

    private void getBundle() {
        Bundle bundle = getIntent().getBundleExtra("data");
        ModelOne modelOne = bundle.getParcelable("obj");
        TextView textView = findViewById(R.id.test_btn);
        textView.setText(modelOne.toString());
    }

    private void testPost() {
        final TextView post = findViewById(R.id.post_view);
        //        post.post(new Runnable() {
        //            @Override
        //            public void run() {
        //                post.setText("width : " + post.getWidth() + " ; height : " + post.getHeight());
        //            }
        //        });

        final LinearLayout postViewGroup = findViewById(R.id.post_view_group);
        postViewGroup.post(new Runnable() {
            @Override
            public void run() {
                post.setText("width : " + postViewGroup.getWidth() + " ; height : " + postViewGroup.getHeight());
            }
        });

    }


    private void testEditShapeRes() {
        TextView tip = findViewById(R.id.text_view);
        GradientDrawable gradientDrawable = (GradientDrawable) tip.getBackground();
        gradientDrawable.setColors(new int[]{Color.parseColor("#00FF00"), Color.parseColor("#FF4081")});
        tip.setBackground(gradientDrawable);
    }

    private void testEditText() {
        final EditText editText = findViewById(R.id.edit_text);
        final TextView tip = findViewById(R.id.tip);
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
                    tip.setText("还需要输入" + (5 - textLen) + "个字");
                } else if (textLen <= 10) {
                    tip.setText(textLen + "/10");
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

    private void testSwitch() {
        final Switch switchOne = findViewById(R.id.switch_one);
        final Switch switchTwo = findViewById(R.id.switch_two);
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
