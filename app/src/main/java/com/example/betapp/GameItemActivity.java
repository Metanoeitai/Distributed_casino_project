package com.example.betapp;

public class GameItemActivity {
    public String gameName;
    public String providerName;
    public float stars;
    public String riskLevel;
    public String betCategory;
    public float minBet;
    public float maxBet;
    public float jackpot;

    public GameItemActivity(String gameName, String providerName, float stars,
                            String riskLevel, String betCategory,
                            float minBet, float maxBet, float jackpot) {
        this.gameName = gameName;
        this.providerName = providerName;
        this.stars = stars;
        this.riskLevel = riskLevel;
        this.betCategory = betCategory;
        this.minBet = minBet;
        this.maxBet = maxBet;
        this.jackpot = jackpot;
    }
}