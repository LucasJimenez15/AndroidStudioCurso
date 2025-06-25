package com.example.activity_linking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    /*AppCompatActivity: Es una clase base en Android que proporciona compatibilidad y funcionalidades
    extendidas en actividades, permitiendo el uso de varias características modernas de la interfaz de usuario.*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonLlamarActividad = findViewById(R.id.buttonLlamar);
        botonLlamarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OtraActivity.class));
            }
        });
    }
}