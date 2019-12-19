package com.iplseason.iplmodel;

import com.opencsv.bean.CsvBindByName;

public class IplMostWicketsData {
    @CsvBindByName(column = "POS")
    public int position;
    @CsvBindByName(column = "PLAYER")
    public String playerName;
    @CsvBindByName(column = "Mat")
    public int matchesPlayed;
    @CsvBindByName(column = "Inns")
    public int innings;
    @CsvBindByName(column = "Ov")
    public double overs;
    @CsvBindByName(column = "Runs")
    public int runs;
    @CsvBindByName(column = "Wkts")
    public int wickets;
    @CsvBindByName(column = "Econ")
    public double economy;
    @CsvBindByName(column = "Avg")
    public double average;
    @CsvBindByName(column = "SR")
    public double strikeRate;
    @CsvBindByName(column = "4w")
    public int fourWickets;
    @CsvBindByName(column = "5w")
    public int fiveWickets;

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
