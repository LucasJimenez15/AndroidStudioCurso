package com.example.aceleradorsinviewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aceleradorsinviewmodel.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private final String PUNTAJE_KEY = "PUNTAJE";
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

        if (savedInstanceState!=null){
            puntaje = savedInstanceState.getInt(PUNTAJE_KEY);
            binding.puntaje.setText(String.valueOf(puntaje));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PUNTAJE_KEY, puntaje);
    }
}