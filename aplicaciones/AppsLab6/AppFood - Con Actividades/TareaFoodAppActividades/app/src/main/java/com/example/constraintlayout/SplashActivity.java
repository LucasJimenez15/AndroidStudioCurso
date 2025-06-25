package com.example.constraintlayout;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Espera de 3 segundos para mostrar la pantalla de splash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Iniciar la MainActivity después de que la pantalla de splash termine
                Intent intencion = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intencion);
                finish(); // Finaliza SplashActivity para no volver a ella al presionar atrás
            }
        }, 3000); // 3000 ms = 3 segundos
    }
}