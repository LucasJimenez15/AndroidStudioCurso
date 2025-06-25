package com.example.constraintlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView reg = findViewById(R.id.registrarseLogin);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, Registro.class));
            }
        });


        TextView olvidarPass = findViewById(R.id.olvidarPasswordLogin);
        olvidarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OlvidoSuPassword.class));
            }
        });

        Button iniciar = findViewById(R.id.botonInciarSesionLogin);
        TextView email = findViewById(R.id.ingresoEmailLogin);
        TextView contra = findViewById(R.id.ingresoPasswordLogin);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(email.getText().toString().trim().isEmpty()  || contra.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese email y contraseña", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intecion = new Intent(MainActivity.this, PaginaHome.class);
                    Bundle b = new Bundle();
                    b.putString("email", email.getText().toString());
                    intecion.putExtras(b);
                    startActivity(intecion);
                }
                
            }

        });

    }
}