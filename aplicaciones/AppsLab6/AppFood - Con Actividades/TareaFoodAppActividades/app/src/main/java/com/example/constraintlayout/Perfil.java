package com.example.constraintlayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Perfil extends AppCompatActivity {

    static final int CODIGO_LLAMADA = 1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        TextView email = findViewById(R.id.user_email);
        TextView nombre = findViewById(R.id.user_name);
        Bundle b = getIntent().getExtras();

        email.setText(b.getString("email"));
        b.putString("email", email.getText().toString());

        nombre.setText(b.getString("nombre"));
        b.putString("nombre", nombre.getText().toString());



        ImageView volver = findViewById(R.id.volverPerfil);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        BottomNavigationView nav = findViewById(R.id.nav);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.home){
                    Intent intecion = new Intent(Perfil.this, PaginaHome.class);
                    Bundle b = getIntent().getExtras();
                    intecion.putExtras(b);
                    startActivity(intecion);
                }
                return item.getItemId() == R.id.profile;
            }

        });


        TextView cerrarSecion = findViewById(R.id.botonCerrarSesionHome);
        cerrarSecion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Perfil.this, MainActivity.class));
            }
        });


        ImageView botonEditar = findViewById(R.id.iconoEditarPerfil);
        botonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intecion = new Intent(Perfil.this, EditarPerfil.class);
                intecion.putExtras(b);
                startActivityForResult(intecion,CODIGO_LLAMADA);
            }
        });


    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
            if ((requestCode == CODIGO_LLAMADA) && (resultCode == RESULT_OK)){

                TextView email = findViewById(R.id.user_email);
                String cambiar = data.getStringExtra("email");
                email.setText(cambiar);

                TextView nombre = findViewById(R.id.user_name);
                cambiar = data.getStringExtra("nombre");
                nombre.setText(cambiar);


            }

    }

}