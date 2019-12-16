package com.iplseason.iplcomparators;

import com.iplseason.iplmodel.IplMostRunsData;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GroupBySorter implements Comparator<IplMostRunsData> {
    private List<Comparator<IplMostRunsData>> listComparators;
    public GroupBySorter(Comparator<IplMostRunsData>... comparators) {
        this.listComparators = Arrays.asList(comparators);
    }

    public int compare(IplMostRunsData mostRunsData1, IplMostRunsData mostRunsData2) {
        for (Comparator<IplMostRunsData> comparator : listComparators) {
            int result = comparator.compare(mostRunsData2, mostRunsData1);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

}
