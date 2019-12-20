package com.iplseason.iplcomparators;

import com.iplseason.iplmodel.IPLMatchesDAO;
import com.iplseason.iplmodel.IplMostRunsData;

import java.util.Comparator;

public class StrikeRateComparator implements Comparator<IPLMatchesDAO> {
    @Override
    public int compare(IPLMatchesDAO object1, IPLMatchesDAO object2) {
        return (int) (object1.strikeRate - object2.strikeRate);
    }
}
