package com.example.constraintlayout;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.*;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentInicioSesion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInicioSesion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentInicioSesion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentInicioSesion.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentInicioSesion newInstance(String param1, String param2) {
        FragmentInicioSesion fragment = new FragmentInicioSesion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar la vista una sola vez
        View view = inflater.inflate(R.layout.fragment_inicio_sesion, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView reg = view.findViewById(R.id.registrarseLogin);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                // Reemplaza 'tu_destino' con el ID de tu destino en el gráfico de navegación
                navController.navigate(R.id.action_fragmentInicioSesion_to_fragmentRegistro);
            }
        });


        TextView olvidarPass = view.findViewById(R.id.olvidarPasswordLogin);
        olvidarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                // Reemplaza 'tu_destino' con el ID de tu destino en el gráfico de navegación
                navController.navigate(R.id.action_fragmentInicioSesion_to_fragmentOlvidoSuPassword);
            }
        });

        // Cambia esto para acceder a TextInputEditText dentro de TextInputLayout
        TextInputEditText emailEditText = view.findViewById(R.id.ingresoEmailLoginInput);
        TextInputEditText contraEditText = view.findViewById(R.id.ingresoPasswordLoginInput);
        Button iniciar = view.findViewById(R.id.botonInciarSesionLogin);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtiene el texto de los TextInputEditText
                if(emailEditText.getText().toString().trim().isEmpty() || contraEditText.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(), "Ingrese email y contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle b = new Bundle();
                    b.putString("email", emailEditText.getText().toString()); // Obtiene el texto del campo de email
                    NavController navController = Navigation.findNavController(view);
                    // Navega al destino con el bundle
                    navController.navigate(R.id.action_fragmentInicioSesion_to_fragmentPaginaHome, b);
                }
            }
        });



    }
}