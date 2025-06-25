package com.example.constraintlayout;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class FragmentEditarPerfil extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentEditarPerfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEditarPerfil.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEditarPerfil newInstance(String param1, String param2) {
        FragmentEditarPerfil fragment = new FragmentEditarPerfil();
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
        View view = inflater.inflate(R.layout.fragment_editar_perfil, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText nombre = view.findViewById(R.id.textViewEditarNombre);
        TextInputEditText email = view.findViewById(R.id.textViewEditarEmail);
        Button enviar = view.findViewById(R.id.botonConfirmarEdicionPerfil);
        Bundle b = getArguments();

        email.setText(b.getString("email"));
        nombre.setText(b.getString("nombre"));

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.putString("email",email.getText().toString());
                b.putString("nombre",nombre.getText().toString());
                NavController navController = Navigation.findNavController(view);
                // Navega al destino con el bundle
                navController.navigate(R.id.action_fragmentEditarPerfil_to_fragmentPerfil, b);
            }
        });



    }
}