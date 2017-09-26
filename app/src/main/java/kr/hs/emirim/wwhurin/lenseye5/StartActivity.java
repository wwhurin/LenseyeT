package kr.hs.emirim.wwhurin.lenseye5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import static kr.hs.emirim.wwhurin.lenseye5.R.layout.activity_start;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();
        setContentView(activity_start);


//        if(Build.VERSION.SDK_INT>=21){
//            getSupportActionBar().hide();
//        }else if(Build.VERSION.SDK_INT<21){
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//        }

        Handler starthandle = new Handler( );
        starthandle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 1000);
    }
}
