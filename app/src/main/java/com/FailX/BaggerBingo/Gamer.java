package com.FailX.BaggerBingo;

import android.os.Parcel;
import android.os.Parcelable;

public class Gamer implements Parcelable {
    public static final Creator<Gamer> CREATOR = new Creator<Gamer>() {
        @Override
        public Gamer createFromParcel(Parcel in) {
            return new Gamer(in);
        }

        @Override
        public Gamer[] newArray(int size) {
            return new Gamer[size];
        }
    };
    private String name;
    private int win = 0;
    private int fail = 0;
    private int joker;

    Gamer(String Name) {
        name = Name;
    }

    protected Gamer(Parcel in) {
        name = in.readString();
        win = in.readInt();
        fail = in.readInt();
        joker = in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public int getJoker() {
        return joker;
    }

    public void setJoker(int joker) {
        this.joker = joker;
    }

    public void resetAll(){
        win = 0;
        fail = 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(win);
        dest.writeInt(fail);
        dest.writeInt(joker);
    }
}
