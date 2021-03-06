package com.iplseason.analyser;

import com.google.gson.Gson;
import com.iplseason.IPLMatchAnalyserException;
import com.iplseason.ipladapters.IPLAdapter;
import com.iplseason.ipladapters.IPLAdapterFactory;
import com.iplseason.iplcomparators.ComparatorFactory;
import com.iplseason.iplcomparators.GroupBySorter;
import com.iplseason.iplmodel.IPLMatchesDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IPLMatchesAnalyzer {

    List<IPLMatchesDAO> iplMatchesList = null;
    List<IPLMatchesDAO> allRounderList = null;
    IPLAdapter iplAdapter = null;

    public IPLMatchesAnalyzer(IPLAdapter iplAdapter) {
        this.iplAdapter = iplAdapter;
    }

    public IPLMatchesAnalyzer() {
        iplMatchesList = new ArrayList<>();
        allRounderList = new ArrayList<>();
    }

    public List<IPLMatchesDAO> getSortedListWithComparator(List<IPLMatchesDAO> iplPlayersList, Comparator comparator) {
        Collections.sort(iplPlayersList, comparator.reversed());
        return iplPlayersList;
    }

    public IPLMatchesDAO compareTwoListsAndGetAllRounder(List<IPLMatchesDAO> sortedRunsList, List<IPLMatchesDAO> sortedWicketList) {
        for (IPLMatchesDAO sortedBatsman : sortedRunsList) {
            for (IPLMatchesDAO sortedBowler : sortedWicketList) {
                if (sortedBatsman.playerName.trim().equalsIgnoreCase(sortedBowler.playerName.trim())) {
                    return sortedBatsman;
                }
            }
        }
        return null;
    }

    public List<IPLMatchesDAO> loadIPLMatchesData(String... csvFilePath) throws IPLMatchAnalyserException {
        iplMatchesList = this.iplAdapter.loadingIPLMatchesData(csvFilePath);
        return iplMatchesList;
    }

    public String getSortedList(Comparator<IPLMatchesDAO>... comparatorsList) {
        if (comparatorsList.length == 1) {
            Collections.sort(iplMatchesList, new GroupBySorter(comparatorsList[0]));
            String sortedStateCensusJson = new Gson().toJson(iplMatchesList);
            return sortedStateCensusJson;
        } else if (comparatorsList.length == 2) {
            Collections.sort(iplMatchesList, new GroupBySorter(comparatorsList[0], comparatorsList[1]));
            String sortedStateCensusJson = new Gson().toJson(iplMatchesList);
            return sortedStateCensusJson;
        }
        return null;
    }

    public List<IPLMatchesDAO> getSortedMergedList(List<IPLMatchesDAO>... iplMatchesDAOS) {
        Comparator bowlingComparator = ComparatorFactory.createComparatorObject(FieldType.BOWLING_AVERAGE);
        Collections.sort(iplMatchesDAOS[0], new GroupBySorter(bowlingComparator.reversed()));
        return iplMatchesDAOS[0];
    }

    public String mergingList(List<IPLMatchesDAO> runsList, List<IPLMatchesDAO> wicketList) {
        for (IPLMatchesDAO playerWicketList : wicketList) {
            for (IPLMatchesDAO playerRunList : runsList) {
                if (playerWicketList.getPlayerName().trim().equalsIgnoreCase(playerRunList.getPlayerName().trim())) {
                    IPLMatchesDAO iplMatchesDAO = new IPLMatchesDAO();
                    iplMatchesDAO.setPlayerName(playerWicketList.playerName);
                    iplMatchesDAO.setBowlingAverage(playerWicketList.average);
                    iplMatchesDAO.setAverage(playerRunList.average);
                    allRounderList.add(iplMatchesDAO);
                }
            }
        }
        List<IPLMatchesDAO> sortedMergedList = this.getSortedMergedList(allRounderList);
        sortedMergedList.forEach(System.out::println);
        String sortedStateCensusJson = new Gson().toJson(sortedMergedList);
        return sortedStateCensusJson;
    }

    public enum PlayerType {
        RUNADAPTER, WICKETADAPTER
    }
}
