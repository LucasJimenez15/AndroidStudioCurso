package com.example.buttons;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton   imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Se presiono el Microfono", Toast.LENGTH_LONG).show();
            }
        });

        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    Toast.makeText(MainActivity.this, "El checkbox esta seleccionado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "El checkbox no esta seleccionado", Toast.LENGTH_LONG).show();
                }
            }
        });

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = findViewById(R.id.radioButton1);
                if (rb.isChecked()){
                    Toast.makeText(MainActivity.this, "El RadioButton 1 esta seleccionado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "El RadioButton 2 esta seleccionado", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void buttonPresionado(View view){
        Toast.makeText(this,getString(R.string.msg_presiono_boton), Toast.LENGTH_LONG).show();
    }
}