package com.example.lawson.androidsummery.tvsupporthtml;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.router.Constant;

@Route(path = Constant.TEXT_VIEW_SUPPORT_HTML)
public class TextViewSupportHtmlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_support_html);

        TextView textView = findViewById(R.id.textView);
        String str = "<font color='#FF4081'>邀请新用户APP下单，立得</font><font color='#303F9F' size='7'>5</font><font color='#00FF00'>元</font>";
        textView.setText(Html.fromHtml(str));
    }
}
