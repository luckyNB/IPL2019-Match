package com.iplseason.iplcomparators;

import com.iplseason.iplmodel.IPLMatchesDAO;

import java.util.Comparator;

public class WicketComparator implements Comparator<IPLMatchesDAO> {
    @Override
    public int compare(IPLMatchesDAO iplMatchesDAO, IPLMatchesDAO t1) {
        return iplMatchesDAO.wickets-t1.wickets;
    }
}
