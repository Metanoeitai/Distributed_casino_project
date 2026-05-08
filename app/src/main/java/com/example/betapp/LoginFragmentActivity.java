package com.example.betapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class LoginFragmentActivity extends Fragment {
    //otan o user kanei login elegxei an ebale ID kai paei sthn o8onh anazhthshs kai "pairnei" kai to ID mazi
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        EditText etPlayerId = view.findViewById(R.id.etPlayerId);
        Button btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String playerId = etPlayerId.getText().toString().trim();
            if (playerId.isEmpty()) {
                Toast.makeText(getContext(), "Type your Player ID ", Toast.LENGTH_SHORT).show();
            } else {
                Bundle args = new Bundle();
                args.putString("playerId", playerId);
                Navigation.findNavController(view)
                        .navigate(R.id.searchFragment, args);
            }
        });

        return view;
    }
}