package com.example.activity_lifecycle;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String tag = "ciclo_vida";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag,"En el evento onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag,"En el evento onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag,"En el evento onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag,"En el evento onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag,"En el evento onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag,"En el evento onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(tag,"En el evento onRestart");
    }
}