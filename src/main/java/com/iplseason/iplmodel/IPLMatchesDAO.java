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

    public IPLMatchesDAO(String playerName, int runs, double average, int fours, int sixes, double strikeRate) {
        this.playerName = playerName;
        this.runs = runs;
        this.average = average;
        this.fours = fours;
        this.sixes = sixes;
        this.strikeRate = strikeRate;
    }

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

    public IPLMatchesDAO(String playerName, int wickets, int fourWickets, int fiveWickets, double bowlingAverage) {
        this.playerName = playerName;
        this.wickets = wickets;
        this.fourWickets = fourWickets;
        this.fiveWickets = fiveWickets;
        this.bowlingAverage = bowlingAverage;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void setBowlingAverage(double bowlingAverage) {
        this.bowlingAverage = bowlingAverage;
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
