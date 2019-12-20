package com.iplseason.iplcomparators;

import com.iplseason.iplmodel.IPLMatchesDAO;

import java.util.Comparator;

public class EconomyComparator implements Comparator<IPLMatchesDAO> {

    public int compare(IPLMatchesDAO object1, IPLMatchesDAO object2) {
        return (int) (object1.economy - object2.economy);
    }
}
