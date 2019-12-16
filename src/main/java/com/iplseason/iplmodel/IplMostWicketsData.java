package com.iplseason.iplmodel;

import com.opencsv.bean.CsvBindByName;

public class IplMostWicketsData {
    @CsvBindByName(column = "POS")
    private int position;
    @CsvBindByName(column = "PLAYER")
    private String playerName;
    @CsvBindByName(column = "Mat")
    private int matchesPlayed;
    @CsvBindByName(column = "Inns")
    private int innings;
    @CsvBindByName(column = "Ov")
    private double overs;
    @CsvBindByName(column = "Runs")
    private int runs;
    @CsvBindByName(column = "Wkts")
    private int wickets;
    @CsvBindByName(column = "Econ")
    private double economy;
    @CsvBindByName(column = "Avg")
    private double average;
    @CsvBindByName(column = "SR")
    private double strikeRate;
    @CsvBindByName(column = "4w")
    private int fourWickets;
    @CsvBindByName(column = "5w")
    private int fiveWickets;

    @Override
    public String toString() {
        return "IplMostWicketsData{" +
                "position=" + position +
                ", playerName='" + playerName + '\'' +
                ", matchesPlayed=" + matchesPlayed +
                ", innings=" + innings +
                ", overs=" + overs +
                ", runs=" + runs +
                ", wickets=" + wickets +
                ", economy=" + economy +
                ", average=" + average +
                ", strikeRate=" + strikeRate +
                ", fourWickets=" + fourWickets +
                ", fiveWickets=" + fiveWickets +
                '}';
    }
}
