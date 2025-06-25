package com.example.constraintlayout;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class FragmentOlvidoSuPassword extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public FragmentOlvidoSuPassword() {
        // Constructor vacío requerido
    }

    public static FragmentOlvidoSuPassword newInstance(String param1, String param2) {
        FragmentOlvidoSuPassword fragment = new FragmentOlvidoSuPassword();
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
        View view = inflater.inflate(R.layout.fragment_olvido_su_password, container, false);

        ImageView volver = view.findViewById(R.id.volverOlvidoSuContraseña);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Para retroceder en el stack de fragmentos
                getParentFragmentManager().popBackStack();
            }
        });

        Button env = view.findViewById(R.id.botonEnviarOlvidoSuContraseña);
        env.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                // Reemplaza 'tu_destino' con el ID de tu destino en el gráfico de navegación
                navController.navigate(R.id.action_fragmentOlvidoSuPassword_to_fragmentVistaOlvidoPassword);
            }
        });

        return view;
    }
}
