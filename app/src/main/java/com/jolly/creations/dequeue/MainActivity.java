package com.jolly.creations.dequeue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void qrcodescan(View view) {

        startActivity(new Intent(MainActivity.this,qrcodescan.class));

    }

    public void queueattender(View view) {

        //startActivity(new Intent(MainActivity.this,qrcodescan.class));

    }
}
