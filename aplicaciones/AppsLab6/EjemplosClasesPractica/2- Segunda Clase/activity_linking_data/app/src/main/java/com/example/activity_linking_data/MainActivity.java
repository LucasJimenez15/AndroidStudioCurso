package com.example.activity_linking_data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final int CODIGO_LLAMADA = 1;
    EditText txtOperando1;
    EditText txtOperando2;
    TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtOperando1 = findViewById(R.id.editTextOperando1);
        txtOperando2 = findViewById(R.id.editTextOperando2);
        textViewResultado = findViewById(R.id.txtViewResultado);

        Button buttonSumar = findViewById(R.id.btnSumar);
        Button buttonRestar = findViewById(R.id.btnRestar);

        buttonSumar.setOnClickListener(this);
        buttonRestar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        Intent intencion = new Intent(this, OperacionesActivity.class);
        Bundle datos = new Bundle();

        if (!estaVacioEditText(txtOperando1) && !estaVacioEditText(txtOperando2)){
            double operando1 = Double.parseDouble(txtOperando1.getText().toString());
            double operando2 = Double.parseDouble(txtOperando2.getText().toString());
            String operador;
            if (v.getId() == R.id.btnSumar){
                operador = "+";
            } else {
                operador = "-";
            }
            datos.putDouble("operando1",operando1);
            datos.putDouble("operando2",operando2);
            datos.putString("operador",operador);

            intencion.putExtras(datos);

            startActivityForResult(intencion,CODIGO_LLAMADA);
        } else {
            Toast.makeText(MainActivity.this,"Debe colocar los operandos",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == CODIGO_LLAMADA) && (resultCode == RESULT_OK)){
            Double resultado = data.getDoubleExtra("resultado",0);
            textViewResultado.setText("El valor retornado es: "+resultado.toString());
        }
    }

    Boolean estaVacioEditText(@NonNull EditText control){
        return (control.getText().toString().isEmpty());
    }
}