package com.example.simpleasynctask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private ProgressBar mProgressBar;

    private static final String TEXT_STATE = "currentText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView1);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setMax(10);

        if (savedInstanceState != null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    public void startTask(View view) {
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setProgress(0);
        mTextView.setText(R.string.napping);

        new SimpleAsyncTask(mTextView, mProgressBar).execute();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }
}