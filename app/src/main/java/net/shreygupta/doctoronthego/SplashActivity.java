package net.shreygupta.doctoronthego;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sp = getSharedPreferences("my_sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();

        e.putString("Admin_Email", "shrey.gupta1904@gmail.com");
        e.putString("Admin_Password", "12345678");

        e.apply();

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(3000);
                    startActivity(new Intent(SplashActivity.this, ClientChooseActivity.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}