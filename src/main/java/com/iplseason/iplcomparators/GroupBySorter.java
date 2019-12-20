package com.iplseason.iplcomparators;

import com.iplseason.iplmodel.IPLMatchesDAO;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GroupBySorter implements Comparator<IPLMatchesDAO> {
    private List<Comparator<IPLMatchesDAO>> listComparators;

    public GroupBySorter(Comparator<IPLMatchesDAO>... comparators) {
        this.listComparators = Arrays.asList(comparators);
    }

    @Override
    public int compare(IPLMatchesDAO mostRunsData1, IPLMatchesDAO mostRunsData2) {
        for (Comparator<IPLMatchesDAO> comparator : listComparators) {
            int result = comparator.compare(mostRunsData1, mostRunsData2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}
