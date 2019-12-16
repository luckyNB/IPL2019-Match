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
    @CsvBindByName(column = "NO")
    public int notOut;
    @CsvBindByName(column = "HS")
    public int highestScore;
    @CsvBindByName(column = "BF")
    public int BF;
    @CsvBindByName(column = "SR")
    public double strikeRate;
    @CsvBindByName(column = "100s")
    public int centuries;
    @CsvBindByName(column = "50s")
    public int fifties;

    public int getPosition() {
        return position;
    }

    public double getAverage() {
        return average;
    }

    public int getFours() {
        return fours;
    }

    public int getSixes() {
        return sixes;
    }

    public int getRuns() {
        return runs;
    }

    public IplMostRunsData(int position, String playerName, int matchesPlayed, int innings, int runs, double average, int fours, int sixes) {
        this.position = position;
        this.playerName = playerName;
        this.matchesPlayed = matchesPlayed;
        this.innings = innings;
        this.runs = runs;
        this.average = average;
        this.fours = fours;
        this.sixes = sixes;
    }

    public IplMostRunsData() {
    }

    @Override
    public String toString() {
        return "IplMostRunsData{" +
                "position=" + position +
                ", playerName='" + playerName + '\'' +
                ", matchesPlayed=" + matchesPlayed +
                ", innings=" + innings +
                ", runs=" + runs +
                ", average=" + average +
                ", fours=" + fours +
                ", sixes=" + sixes +
                '}';
    }
}
