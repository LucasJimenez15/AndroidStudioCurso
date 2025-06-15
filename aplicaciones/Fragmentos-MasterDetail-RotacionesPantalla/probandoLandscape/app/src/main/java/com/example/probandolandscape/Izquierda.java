package com.example.probandolandscape;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Izquierda extends Fragment {

    EditText txt;
    Button btn;
    Enviar enviar;
    View rootView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.izquierda, container, false);
        //AQUI EN ESTE METODO SE CREA LA INTERFAZ GRAFICA, EL DIESÑO
        txt = rootView.findViewById(R.id.textoAenviar);
        btn = rootView.findViewById(R.id.botonEnv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String msj;
                msj = txt.getText().toString();
                enviar.enviarTexto(msj);

            }
        });
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);

        try {
            enviar =  (Enviar) activity;
        }catch (Exception e){
            throw new ClassCastException("nesceitas msj");
        }

    }
}
