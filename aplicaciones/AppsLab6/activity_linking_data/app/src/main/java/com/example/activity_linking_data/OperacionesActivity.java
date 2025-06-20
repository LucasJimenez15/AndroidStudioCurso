package com.example.activity_linking_data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OperacionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operaciones);

        Button buttonVolver = findViewById(R.id.buttonRetornar);

        Bundle datos = getIntent().getExtras();
        Double operando1 = datos.getDouble("operando1");
        Double operando2 = datos.getDouble("operando2");
        String operador = datos.getString("operador");
        Double resultado = operador.equals("+") ? operando1+operando2 : operando1-operando2;

        TextView textViewExpresion = findViewById(R.id.txtViewExpresion);
        textViewExpresion.setText(operando1+operador+operando2+"="+resultado);

        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultados = new Intent();
                resultados.putExtra("resultado",resultado);
                setResult(RESULT_OK, resultados);
                finish();
            }
        });
    }
}