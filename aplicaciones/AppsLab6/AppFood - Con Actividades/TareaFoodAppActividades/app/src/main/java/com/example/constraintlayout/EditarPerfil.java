package com.example.constraintlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditarPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView nombre = findViewById(R.id.textViewEditarNombre);
        TextView email = findViewById(R.id.textViewEditarEmail);
        Button enviar = findViewById(R.id.botonConfirmarEdicionPerfil);
        Bundle b = getIntent().getExtras();

        email.setText(b.getString("email"));
        nombre.setText(b.getString("nombre"));

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intecion = new Intent(EditarPerfil.this, Perfil.class);
                b.putString("email",email.getText().toString());
                b.putString("nombre",nombre.getText().toString());
                intecion.putExtras(b);
                setResult(RESULT_OK, intecion);
                startActivity(intecion);
            }
        });


    }
}