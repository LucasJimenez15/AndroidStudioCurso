package com.example.operacionesmatematicas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import static com.example.operacionesmatematicas.R.id.*;

public class MainActivity extends AppCompatActivity {

    EditText campo1, campo2;
    TextView resultado;
    Button suma, resta, multi, divi, borrarRes;
    Float num1, num2;

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

        campo1 = (EditText) findViewById(R.id.campo1);
        campo2 = (EditText) findViewById(R.id.campo2);
        resultado = (TextView) findViewById(R.id.resultado);
        suma = (Button) findViewById(R.id.suma);
        resta = (Button) findViewById(R.id.resta);
        divi = (Button) findViewById(R.id.divi);
        multi = (Button) findViewById(R.id.multi);
        

    }


    public void onClick(View view) {

        try {

            if(view.getId() == R.id.borrarRes){
                borrarResultado();
            }

            num1 = Float.parseFloat(campo1.getText().toString());
            num2 = Float.parseFloat(campo2.getText().toString());

            if(view.getId() == R.id.suma){
                sumar();
            }
            if(view.getId() == R.id.resta){
                restar();
            }
            if(view.getId() == R.id.multi){
                multiplicar();
            }
            if(view.getId() == R.id.divi){
                dividir();
            }

        }catch (Exception e){
            Toast.makeText(this,"Error, debe ingresar los numeros",Toast.LENGTH_SHORT).show();
        }

    }

    private void borrarResultado() {
        if(resultado.getText().length() > 0){
            resultado.setText("");
        }
    }

    private void sumar() {
        float suma = num1 + num2;
        resultado.setText(String.valueOf(suma));
    }
    private void restar() {
        float resta = num1 - num2;
        resultado.setText(String.valueOf(resta));
    }
    private void multiplicar() {
        float multi = num1 * num2;
        resultado.setText(String.valueOf(multi));
    }
    private void dividir() {
        if (num2 != 0) {
            float divi = num1 / num2;
            resultado.setText(String.valueOf(divi));
        } else {
            resultado.setText("Error: División por cero");
        }
    }

}