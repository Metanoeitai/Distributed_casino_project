package com.example.betapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class SearchFragmentActivity extends Fragment {

    private String playerId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // pairnoyme to payerID apo to login
        if (getArguments() != null) {
            playerId = getArguments().getString("playerId");
        }

        EditText etStars = view.findViewById(R.id.etStars);
        RadioGroup rgRisk = view.findViewById(R.id.rgRisk);
        RadioGroup rgBet = view.findViewById(R.id.rgBet);
        Button btnSearch = view.findViewById(R.id.btnSearch);
        Button btnAddBalance = view.findViewById(R.id.btnAddBalance);

        btnSearch.setOnClickListener(v -> {
            String stars = etStars.getText().toString().trim();
            if (stars.isEmpty()) stars = "0";

            // Risk level
            String risk = "all";
            int riskId = rgRisk.getCheckedRadioButtonId();
            if (riskId == R.id.rbLow) risk = "low";
            else if (riskId == R.id.rbMedium) risk = "medium";
            else if (riskId == R.id.rbHigh) risk = "high";

            // Bet limit
            String bet = "all";
            int betId = rgBet.getCheckedRadioButtonId();
            if (betId == R.id.rbBet1) bet = "$";
            else if (betId == R.id.rbBet2) bet = "$$";
            else if (betId == R.id.rbBet3) bet = "$$$";

            Bundle args = new Bundle();
            args.putString("playerId", playerId);
            args.putString("stars", stars);
            args.putString("risk", risk);
            args.putString("bet", bet);

            Navigation.findNavController(view)
                    .navigate(R.id.gameListFragment, args);
        });

        btnAddBalance.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("playerId", playerId);
            Navigation.findNavController(view)
                    .navigate(R.id.addBalanceFragment, args);
        });

        return view;
    }
}