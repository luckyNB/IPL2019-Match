package com.iplseason.iplcomparators;

import com.iplseason.iplmodel.IplMostRunsData;

import java.util.Comparator;

public class RunsComparator implements Comparator<IplMostRunsData> {
    @Override
    public int compare(IplMostRunsData object1, IplMostRunsData object2) {
        return (object1.runs - object2.runs);
    }
}
