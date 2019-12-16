package com.iplseason.iplcomparators;

import com.iplseason.iplmodel.IplMostRunsData;

import java.util.Comparator;

public class SixexComparator implements Comparator<IplMostRunsData> {
    @Override
    public int compare(IplMostRunsData object1, IplMostRunsData object2) {
        return (int) (object1.getSixes() - object2.getSixes());
    }
}
