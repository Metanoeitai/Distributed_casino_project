package com.example.betapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerConnection{

    private String ip;
    private int port;

    public ServerConnection(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public interface Callback<T>{
        void onSuccess(T result);
        void onError(String error);
    }

    public void search(String betCategory, String riskLevel, String minStars, Callback<List<GameResult>> callback){
        new Thread(() -> {
            try(
                    Socket socket = new Socket(ip, port);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));)
            {
                    out.println("SEARCH");
                    out.println(betCategory);
                    out.println(riskLevel);
                    out.println(minStars);

                    List<GameResult> results = new ArrayList<>();
                    while(true){
                        String firstField = in.readLine();
                        if(firstField == null || firstField.equals("END")){
                            break;
                        }

                        GameResult game = new GameResult();
                        game.gameName = firstField;
                        game.providerName = in.readLine();
                        game.gameLogo = in.readLine();
                        game.stars = in.readLine();
                        game.noOfVotes = in.readLine();
                        game.minBet = in.readLine();
                        game.maxBet = in.readLine();
                        game.riskLevel = in.readLine();
                        game.betCategory = in.readLine();
                        game.jackpot = in.readLine();
                        results.add(game);

                    }
                    callback.onSuccess(results);
            }catch(Exception e){
                callback.onError(e.getMessage());
            }
        }).start();
    }

    public void addBalance(String playerId, String amount, Callback<String> callback){
        new Thread(() -> {
            try(
                    Socket socket = new Socket(ip, port);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));)
            {
                    out.println("ADD_BALANCE");
                    out.println(playerId);
                    out.println(amount);

                    String status = in.readLine();
                    callback.onSuccess(status);
            }catch(Exception e){
                callback.onError(e.getMessage());
            }
        }).start();
    }

    public void play(String gameName, String playerId, String betAmount, Callback<String> callback){
        new Thread(() -> {
            try(
                    Socket socket = new Socket(ip, port);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));)
            {
                    out.println("PLAY");
                    out.println(gameName);
                    out.println(playerId);
                    out.println(betAmount);

                    String status = in.readLine();
                    String result = in.readLine();
                    String type = in.readLine();

                    callback.onSuccess(status + "|" + result + "|" + type);
            }catch (Exception e){
                callback.onError(e.getMessage());
            }
        }).start();
    }


    public void vote(String gameName, String stars, Callback<String> callback){
        new Thread(() -> {
            try(
                    Socket socket = new Socket(ip, port);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));)
            {
                    out.println("VOTE");
                    out.println(gameName);
                    out.println(stars);

                    String status = in.readLine();
                    callback.onSuccess(status);
            }catch (Exception e){
                callback.onError(e.getMessage());
            }
        }).start();
    }



    public static class GameResult {
        public String gameName;
        public String providerName;
        public String gameLogo;
        public String stars;
        public String noOfVotes;
        public String minBet;
        public String maxBet;
        public String riskLevel;
        public String betCategory;
        public String jackpot;
    }
}