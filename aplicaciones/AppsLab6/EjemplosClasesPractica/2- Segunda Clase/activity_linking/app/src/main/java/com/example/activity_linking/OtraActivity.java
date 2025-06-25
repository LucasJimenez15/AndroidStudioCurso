package com.example.activity_linking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OtraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra);

        Button botonRetornarActividadPrincipal = findViewById(R.id.buttonRetornar);
        botonRetornarActividadPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                /*finish(): Este método cierra la actividad actual (OtraActivity) y devuelve el control a la
                actividad que la inició (en este caso, MainActivity).
                Propósito: El método finish() no destruye la aplicación completa, solo la actividad actual.
                Cuando OtraActivity se cierra, el usuario regresa automáticamente a la actividad anterior
                (en este caso, MainActivity).
                Comportamiento: Al llamar a finish(), el sistema elimina OtraActivity de la pila de actividades
                (back stack), lo que significa que MainActivity será la actividad visible nuevamente.*/
            }
        });
    }
}