package com.example.simrandeepsingh.recyclerviewproject;

/**
 * Created by simrandeepsingh on 18/08/17.
 */

public class Listitem {
    private String tvname;
    private  String tvver;
    private String tvlevel;


    public Listitem(String tvname, String tvver,String tvlevel) {
        this.tvname=tvname;
        this.tvver=tvver;
        this.tvlevel=tvlevel;
    }


    public String getTvver() {
        return tvver;
    }

    public String getTvname() {
        return tvname;
    }

    public String getTvlevel() {
        return tvlevel;
    }
}
