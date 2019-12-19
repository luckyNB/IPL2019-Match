package com.iplseason.ipladapters;

import com.iplseason.IPLMatchAnalyserException;
import com.iplseason.iplmodel.IPLMatchesDAO;
import com.iplseason.iplmodel.IplMostRunsData;

import java.util.ArrayList;
import java.util.List;

public class IPLMostWicketsAdapter extends IPLAdapter {
    List<IPLMatchesDAO> daoList = null;

    public IPLMostWicketsAdapter() {
        daoList = new ArrayList<>();
    }

    @Override
    public List<IPLMatchesDAO> loadingIPLMatchesData(String... csvFilePath) throws IPLMatchAnalyserException {
        daoList = super.loadingIPLMatchesData(IplMostRunsData.class, csvFilePath[0]);
        return daoList;

    }
}
