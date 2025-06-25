package com.example.constraintlayout;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class FragmentPerfil extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static FragmentPerfil newInstance(String param1, String param2) {
        FragmentPerfil fragment = new FragmentPerfil();
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
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener el Bundle y el email
        Bundle bundle = getArguments();
        String email = null; // Inicializamos como null
        String nombre = null;
        if (bundle != null) {
            email = bundle.getString("email"); // Intentamos obtener el email
            nombre = bundle.getString("nombre");
        }

        // Encontrar el TextView de la vista
        TextView emailTextView = view.findViewById(R.id.user_email);
        TextView nombreTextView = view.findViewById(R.id.user_name);

        // Verificar si el email es nulo antes de establecer el texto
        if (email != null) {
            emailTextView.setText(email); // Cambiar el texto del TextView
        } else {
            emailTextView.setText("No se recibió email"); // Mensaje predeterminado
        }

        // Verificar si el email es nulo antes de establecer el texto
        if (nombre != null) {
            nombreTextView.setText(nombre); // Cambiar el texto del TextView
        }

        ImageView volver = view.findViewById(R.id.volverPerfil);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });

        BottomNavigationView nav = view.findViewById(R.id.nav);

        // Obtener NavController desde la vista raíz del fragmento
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);

        // Conectar el BottomNavigationView con el NavController
        NavigationUI.setupWithNavController(nav, navController);

        nav.setOnNavigationItemSelectedListener(item -> {
            Fragment currentFragment = getParentFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);

            if (item.getItemId() == R.id.profile) {
                if (!(currentFragment instanceof FragmentPerfil)) {
                    navController.navigate(R.id.action_fragmentPaginaHome_to_fragmentPerfil,bundle);
                }
                return true;
            } else if (item.getItemId() == R.id.shop) {
                // Navegar a otro fragmento, si es necesario
                return true;
            } else if (item.getItemId() == R.id.home) {
                if (!(currentFragment instanceof FragmentPaginaHome)) {
                    navController.navigate(R.id.action_fragmentPerfil_to_fragmentPaginaHome,bundle);
                }
                return true;
            }
            return false;
        });


        TextView cerrarSecion = view.findViewById(R.id.botonCerrarSesionHome);
        cerrarSecion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_fragmentPerfil_to_fragmentInicioSesion,bundle);
            }
        });


        ImageView botonEditar = view.findViewById(R.id.edit_icon);
        botonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_fragmentPerfil_to_fragmentEditarPerfil,bundle);
            }
        });



    }
}
