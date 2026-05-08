package com.example.betapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class GameAdapterActivity extends RecyclerView.Adapter<GameAdapterActivity.GameViewHolder> {

    public static interface OnPlayClickListener {
        void onPlayClick(GameItemActivity game);
    }

    private List<GameItemActivity> games;
    private OnPlayClickListener listener;

    public GameAdapterActivity(List<GameItemActivity> games, OnPlayClickListener listener) {
        this.games = games;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        GameItemActivity game = games.get(position);
        holder.tvGameName.setText(game.gameName);
        holder.tvProvider.setText("Provider: " + game.providerName);
        holder.tvStars.setText("⭐ " + game.stars);
        holder.tvRisk.setText("Risk: " + game.riskLevel);
        holder.tvBet.setText(game.betCategory);
        holder.btnPlay.setOnClickListener(v -> listener.onPlayClick(game));
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    static class GameViewHolder extends RecyclerView.ViewHolder {
        TextView tvGameName, tvProvider, tvStars, tvRisk, tvBet;
        Button btnPlay;

        GameViewHolder(View itemView) {
            super(itemView);
            tvGameName = itemView.findViewById(R.id.tvGameName);
            tvProvider = itemView.findViewById(R.id.tvProvider);
            tvStars = itemView.findViewById(R.id.tvStars);
            tvRisk = itemView.findViewById(R.id.tvRisk);
            tvBet = itemView.findViewById(R.id.tvBet);
            btnPlay = itemView.findViewById(R.id.btnPlay);
        }
    }
}