package com.example.aceleradororiginal;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.aceleradororiginal.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int puntaje = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.botonMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puntaje++;
                binding.puntaje.setText(String.valueOf(puntaje));
            }
        });

        binding.botonMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puntaje--;
                binding.puntaje.setText(String.valueOf(puntaje));
            }
        });
    }
}