package com.example.constraintlayout;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentVistaOlvidoPassword extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public FragmentVistaOlvidoPassword() {
        // Constructor vacío requerido
    }

    public static FragmentVistaOlvidoPassword newInstance(String param1, String param2) {
        FragmentVistaOlvidoPassword fragment = new FragmentVistaOlvidoPassword();
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

        View view = inflater.inflate(R.layout.fragment_vista_olvido_password, container, false);

        Button volver = view.findViewById(R.id.botonHechoOlvidoSuContraseña);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Regresar a la pantalla anterior con popBackStack
                getParentFragmentManager().popBackStack();
            }
        });

        return view;
    }
}
