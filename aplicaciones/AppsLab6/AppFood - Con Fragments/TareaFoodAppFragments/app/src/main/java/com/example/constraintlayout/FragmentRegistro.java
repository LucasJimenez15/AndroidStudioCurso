package com.example.constraintlayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentRegistro extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public FragmentRegistro() {
        // Constructor vacío requerido
    }

    public static FragmentRegistro newInstance(String param1, String param2) {
        FragmentRegistro fragment = new FragmentRegistro();
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

        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView inSesion = view.findViewById(R.id.iniciarSecionbotonRegistro);
        inSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                // Reemplaza 'tu_destino' con el ID de tu destino en el gráfico de navegación
                navController.navigate(R.id.action_fragmentRegistro_to_fragmentInicioSesion);
            }
        });

        Button regBoton = view.findViewById(R.id.botonRegistrar);
        regBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Se registró correctamente", Toast.LENGTH_SHORT).show();
                // Puedes añadir lógica aquí o navegar a otro fragmento si lo deseas
                getParentFragmentManager().popBackStack();
            }
        });

    }
}
