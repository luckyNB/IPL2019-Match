package com.iplseason.iplcomparators;

import com.iplseason.iplmodel.IPLMatchesDAO;

import java.util.Comparator;

public class FourAndFiveWicketComparator implements Comparator<IPLMatchesDAO> {
    @Override
    public int compare(IPLMatchesDAO object1, IPLMatchesDAO object2) {
        return (object1.fourWickets + object1.fiveWickets) - (object2.fourWickets + object2.fiveWickets);
    }
}
