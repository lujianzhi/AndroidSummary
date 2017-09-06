package com.example.lawson.androidsummery.thread.asynctask;

import android.os.AsyncTask;

/**
 * Created by Ian.Lu on 2017/9/5.
 * Project : AndroidSummary
 */

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

    private IUpdateUI iUpdateUI;

    public MyAsyncTask(IUpdateUI iUpdateUI) {
        this.iUpdateUI = iUpdateUI;
    }

    public interface IUpdateUI {

        void onProgressUpdate(int progress);

        void onPostExecute();

        void onCancelled();

        void onCancelled(Void aVoid);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        for (int i = 0; i < 10; i++) {
            publishProgress(i * 10);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isCancelled()) {
                break;
            }
        }
        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        iUpdateUI.onCancelled();
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
        iUpdateUI.onCancelled(aVoid);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        iUpdateUI.onProgressUpdate(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        iUpdateUI.onPostExecute();
    }
}
