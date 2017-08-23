package com.example.lawson.androidsummery.mvp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Ian.Lu on 2017/8/20.
 * Project : AndroidSummary
 */

public class NetProgressDialogHelper extends Handler {

    public static final int HIDE = 0;
    public static final int SHOW = 1;
    public static final String TITLE = "title";
    public static final String MESSAGE = "message";

    private ProgressDialog progressDialog;

    public NetProgressDialogHelper(Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
        }
    }

    private void show(String title, String message) {
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    private void hide() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        int what = msg.what;
        Bundle data = msg.getData();
        String message = "";
        String title = "";
        if (data != null) {
            message = data.getString(TITLE, "");
            title = data.getString(MESSAGE, "");
        }

        switch (what) {
            case HIDE:
                hide();
                break;

            case SHOW:
                show(title, message);
                break;

            default:
                hide();
                break;
        }
    }
}
