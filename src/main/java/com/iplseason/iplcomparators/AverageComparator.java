package com.iplseason.iplcomparators;

import com.iplseason.iplmodel.IplMostRunsData;

import java.util.Comparator;

public class AverageComparator implements Comparator<IplMostRunsData> {
    @Override
    public int compare(IplMostRunsData object1, IplMostRunsData object2) {
        return (int) (object1.getAverage() - object2.getAverage());

    }
}
