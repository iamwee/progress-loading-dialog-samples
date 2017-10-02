package com.github.iamwee.progressdialogsample;

import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressLoadingDialog.dismiss(getSupportFragmentManager());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ProgressLoadingDialog.dismiss(getSupportFragmentManager());
            }
        }, 3000);
        ProgressLoadingDialog.show(getSupportFragmentManager());
    }

}
