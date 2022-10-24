package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    SimpleAsyncTask(TextView tv, ProgressBar pb) {
        this.mTextView = new WeakReference<TextView>(tv);
        this.mProgressBar = new WeakReference<ProgressBar>(pb);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random random = new Random();
        int n = random.nextInt(20);
        int s = n * 200;
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(s/10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i);
        }

        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onPostExecute(String result) {
        mProgressBar.get().setVisibility(View.GONE);
        mTextView.get().setText(result);
    }

    @Override
    public void onProgressUpdate(Integer... progress) {
        mProgressBar.get().setProgress(progress[0]);
    }
}
