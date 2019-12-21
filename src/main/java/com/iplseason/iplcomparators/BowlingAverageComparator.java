package com.iplseason.iplcomparators;

import com.iplseason.iplmodel.IPLMatchesDAO;

import java.util.Comparator;

public class BowlingAverageComparator implements Comparator<IPLMatchesDAO> {
    @Override
    public int compare(IPLMatchesDAO object1, IPLMatchesDAO object2) {
        return (int) ((object1.bowlingAverage+object1.average)-(object2.bowlingAverage+object2.average));
    }
}
