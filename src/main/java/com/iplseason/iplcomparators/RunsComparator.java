package com.iplseason.iplcomparators;

import com.iplseason.iplmodel.IPLMatchesDAO;
import com.iplseason.iplmodel.IplMostRunsData;

import java.util.Comparator;

public class RunsComparator implements Comparator<IPLMatchesDAO> {
    @Override
    public int compare(IPLMatchesDAO object1, IPLMatchesDAO object2) {
        return (object1.runs - object2.runs);
    }
}
