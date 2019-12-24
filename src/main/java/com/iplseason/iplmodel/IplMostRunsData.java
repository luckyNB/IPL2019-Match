package com.iplseason.iplmodel;

import com.opencsv.bean.CsvBindByName;

public class IplMostRunsData {
    @CsvBindByName(column = "POS")
    public int position;
    @CsvBindByName(column = "PLAYER")
    public String playerName;
    @CsvBindByName(column = "Mat")
    public int matchesPlayed;
    @CsvBindByName(column = "Inns")
    public int innings;
    @CsvBindByName(column = "Runs")
    public int runs;
    @CsvBindByName(column = "Avg")
    public double average;
    @CsvBindByName(column = "4s")
    public int fours;
    @CsvBindByName(column = "6s")
    public int sixes;
    @CsvBindByName(column = "SR")
    public double strikeRate;

    public IplMostRunsData() {
    }

    public IplMostRunsData(String playerName, int matchesPlayed, int innings) {
        this.playerName = playerName;
        this.matchesPlayed = matchesPlayed;
        this.innings = innings;
    }
}
