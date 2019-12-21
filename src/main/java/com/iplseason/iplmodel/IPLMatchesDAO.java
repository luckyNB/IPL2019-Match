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
    public double bowlingAverage;

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

    public IPLMatchesDAO() {
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void setEconomy(double economy) {
        this.economy = economy;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getRuns() {
        return runs;
    }

    public double getAverage() {
        return average;
    }

    public void setBowlingAverage(double bowlingAverage) {
        this.bowlingAverage = bowlingAverage;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    @Override
    public String toString() {
        return "IPLMatchesDAO{" +
                "playerName='" + playerName + '\'' +
                ", runs=" + runs +
                ", average=" + average +
                ", fours=" + fours +
                ", sixes=" + sixes +
                ", strikeRate=" + strikeRate +
                ", wickets=" + wickets +
                ", economy=" + economy +
                ", fourWickets=" + fourWickets +
                ", fiveWickets=" + fiveWickets +
                ", bowlingAverage=" + bowlingAverage +
                '}';
    }
}
