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
    @CsvBindByName(column = "Runs")
    private int runs;
    @CsvBindByName(column = "Avg")
    private double average;
    @CsvBindByName(column = "4s")
    private int fours;
    @CsvBindByName(column = "6s")
    private int sixes;
}
