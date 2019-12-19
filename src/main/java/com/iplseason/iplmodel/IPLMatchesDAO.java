package com.iplseason.iplmodel;

public class IPLMatchesDAO {
    public String playerName;
    public int runs;
    public double average;
    public int fours;
    public int sixes;
    public double strikeRate;

    public int wickets;
    public double economy;
    public int fourWickets;
    public int fiveWickets;


    public IPLMatchesDAO(IplMostRunsData mostRunsData) {
        playerName = mostRunsData.playerName;
        runs = mostRunsData.runs;
        average = mostRunsData.average;
        fours = mostRunsData.fours;
        sixes = mostRunsData.sixes;
        strikeRate = mostRunsData.strikeRate;
    }

    public IPLMatchesDAO(IplMostWicketsData wicketsData) {
        playerName = wicketsData.playerName;
        wickets = wicketsData.wickets;
        economy = wicketsData.economy;
        average = wicketsData.average;
        fiveWickets = wicketsData.fiveWickets;
        fourWickets = wicketsData.fourWickets;
        strikeRate = wicketsData.strikeRate;
        runs = wicketsData.runs;
    }
}
