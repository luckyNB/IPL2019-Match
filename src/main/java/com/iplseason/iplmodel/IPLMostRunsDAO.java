package com.iplseason.iplmodel;

public class IPLMostRunsDAO {
    public String playerName;
    public int runs;
    public double average;
    public int fours;
    public int sixes;
    public double strikeRate;

    public IPLMostRunsDAO(IplMostRunsData mostRunsData) {
        playerName=mostRunsData.playerName;
        runs=mostRunsData.runs;
        average=mostRunsData.average;
        fours=mostRunsData.fours;
        sixes=mostRunsData.sixes;
        strikeRate=mostRunsData.strikeRate;
    }
}
