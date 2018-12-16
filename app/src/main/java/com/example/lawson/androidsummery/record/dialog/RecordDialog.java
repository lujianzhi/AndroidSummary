package com.example.lawson.androidsummery.record.dialog;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;

/**
 * 录音对话框
 *
 * @author lujianzhi
 * @className RecordDialog
 * @date 2017/11/14 11:16
 **/
public class RecordDialog extends DialogFragment implements View.OnClickListener {

    private ImageView recordAniIv;
    private TextView recordingSureTv;
    private TextView recordingCancelTv;

    private IRecordDialogClickListener recordDialogClickListener;

    public void setRecordDialogClickListener(IRecordDialogClickListener iRecordDialogClickListener) {
        this.recordDialogClickListener = iRecordDialogClickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_record_layout, null);
        recordAniIv = view.findViewById(R.id.record_iv);
        recordingSureTv = view.findViewById(R.id.recording_sure_tv);
        recordingCancelTv = view.findViewById(R.id.recording_cancel_tv);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        initViews();
    }

    private void initViews() {
        AnimationDrawable animationDrawable = (AnimationDrawable) recordAniIv.getDrawable();
        animationDrawable.start();

        recordingSureTv.setOnClickListener(this);
        recordingCancelTv.setOnClickListener(this);
    }

    public ImageView getRecordAniIv() {
        return recordAniIv;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recording_sure_tv:
                if (recordDialogClickListener != null) {
                    recordDialogClickListener.confirm();
                }
                dismiss();
                break;

            case R.id.recording_cancel_tv:
                if (recordDialogClickListener != null) {
                    recordDialogClickListener.cancel();
                }
                dismiss();
                break;

            default:
                break;
        }
    }

    public interface IRecordDialogClickListener {
        void cancel();

        void confirm();
    }
}
