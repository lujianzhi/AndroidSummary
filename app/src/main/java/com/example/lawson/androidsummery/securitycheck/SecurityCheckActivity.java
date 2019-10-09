package com.example.lawson.androidsummery.securitycheck;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;

public class SecurityCheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_check);

        setButtonClick(R.id.root, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextView(R.id.root_text, SecurityCheckUtils.checkRoot() ? "rooted" : "not root");
            }
        });

        setButtonClick(R.id.vm, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextView(R.id.vm_text,
                            SecurityCheckUtils.checkVM(SecurityCheckActivity.this) ? "vm" : "not vm");
            }
        });

        setButtonClick(R.id.multi_app, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextView(R.id.multi_app_text,
                            SecurityCheckUtils.checkMultiApp(SecurityCheckActivity.this) ? "多开" : "未多开");
            }
        });

        setButtonClick(R.id.xposed, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextView(R.id.xposed_text,
                            SecurityCheckUtils.checkXposed() ? "xposed" : "not xposed");
            }
        });

    }

    private void setButtonClick(@IdRes int resId, View.OnClickListener listener) {
        findViewById(resId).setOnClickListener(listener);
    }

    private void setTextView(@IdRes int resId, String text) {
        TextView textView = findViewById(resId);
        textView.setText(text);
    }
}
