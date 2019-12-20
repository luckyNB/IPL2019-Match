package com.iplseason.analyser;

import com.google.gson.Gson;
import com.iplseason.IPLMatchAnalyserException;
import com.iplseason.ipladapters.IPLAdapter;
import com.iplseason.ipladapters.IPLAdapterFactory;
import com.iplseason.iplcomparators.GroupBySorter;
import com.iplseason.iplmodel.IPLMatchesDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IPLMatchesAnalyzer {

    List<IPLMatchesDAO> iplMatchesList = null;

    public IPLMatchesAnalyzer() {
        iplMatchesList = new ArrayList<>();

    }

    public List<IPLMatchesDAO> loadIPLMatchecData(PlayerType playerType, String... csvFilePath) throws IPLMatchAnalyserException {
        IPLAdapter censusAdapter = IPLAdapterFactory.createIPLAdapterObject(playerType);
        iplMatchesList = censusAdapter.loadingIPLMatchesData(csvFilePath);
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

    public enum PlayerType {
        RUNADAPTER, WICKETADAPTER
    }


}
