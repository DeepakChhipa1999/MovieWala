package com.tmbd.cinematics;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {

    //private GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity.this.finish();
                //saveIntoSession("isLogin",true);

                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);


            }
        }, 3000);
    }

}
