package com.example.aceleradorconviewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.aceleradorconviewmodel.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.puntaje.setText(String.valueOf(viewModel.getPuntaje()));

        binding.botonMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.incrementar();
                binding.puntaje.setText(String.valueOf(viewModel.getPuntaje()));
            }
        });

        binding.botonMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.decrementar();
                binding.puntaje.setText(String.valueOf(viewModel.getPuntaje()));
            }
        });

    }
}