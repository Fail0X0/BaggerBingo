package com.FailX.BaggerBingo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

class Game implements Parcelable {

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
    private List<Gamer> player;
    private String language = "de";
    private int gamer_count = 0;                                                                            //for run through the player list
    private int max_round = 5;
    private int max_jokers = 2;
    private int round = 1;
    private int level = 3;

    public int getEntry() {
        return entry;
    }

    public void setEntry(int entry) {
        this.entry = entry;
    }

    private int entry = 1;

    protected Game(Parcel in) {
        player = in.createTypedArrayList(Gamer.CREATOR);
        max_round = in.readInt();
        round = in.readInt();
        gamer_count = in.readInt();
        language = in.readString();
        max_jokers = in.readInt();
        level = in.readInt();
        entry = in.readInt();
    }

    public Game() {
        player = new ArrayList<>();
    }

    public void sortWinners() {
        Gamer temp = new Gamer("temp");
        for (int i = 1; i < player.size(); i++) {
            for (int j = 0; j < player.size() - i; j++) {
                if (player.get(j).getWin() < player.get(j + 1).getWin()) {
                    temp = player.get(j);
                    player.set(j, player.get(j + 1));
                    player.set(j + 1, temp);
                }
            }
        }
    }

    Gamer getCurrentPlayer() {
        return player.get(gamer_count % player.size());
    }

    public void nextPlayer() {
        gamer_count++;
    }

    public List<Gamer> getPlayerlist() {
        return player;
    }

    public void setPlayerlist(List<Gamer> player) {
        this.player = player;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getMax_round() {
        return max_round;
    }

    public void setMax_round(int max_round) {
        this.max_round = max_round;
    }

    public int getMax_jokers() {
        return max_jokers;
    }

    public void setMax_jokers(int max_jokers) {
        this.max_jokers = max_jokers;
    }

    public int getRound() {
        return round;
    }

    public void restAll(){
        for (int i = 0; i < player.size(); i++) {
            player.get(i).resetAll();
            player.get(i).setJoker(max_jokers);
        }
        gamer_count = 0;
    }

    public void setRound() {
        if (player.get(player.size() - 1) == getCurrentPlayer())
            round++;
    }

    public void clearRound() {
        round = 1;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(player);
        dest.writeInt(max_round);
        dest.writeInt(round);
        dest.writeInt(gamer_count);
        dest.writeString(language);
        dest.writeInt(max_jokers);
        dest.writeInt(level);
        dest.writeInt(entry);
    }
}
