package com.example.layout_linear_horizontal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button botonSaludar;
        EditText editTextNombres;

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        botonSaludar = findViewById(R.id.buttonSaludar);
        editTextNombres = findViewById(R.id.editTextNombre);

        botonSaludar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombres;
                nombres = editTextNombres.getText().toString();
                Toast.makeText(MainActivity.this, "Hola "+nombres,Toast.LENGTH_LONG).show();
            }
        });
    }
}