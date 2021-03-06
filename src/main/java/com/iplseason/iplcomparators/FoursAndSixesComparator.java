package com.iplseason.iplcomparators;

import com.iplseason.iplmodel.IPLMatchesDAO;
import com.iplseason.iplmodel.IplMostRunsData;

import java.util.Comparator;

public class FoursAndSixesComparator implements Comparator<IPLMatchesDAO> {
    @Override
    public int compare(IPLMatchesDAO object1, IPLMatchesDAO object2) {
        return ((object1.sixes * 6) + (object1.fours * 4)) - ((object2.sixes * 6) + (object2.fours * 4));    }
}
