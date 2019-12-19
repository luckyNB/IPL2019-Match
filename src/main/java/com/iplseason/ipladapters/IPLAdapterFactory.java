package com.iplseason.ipladapters;

import com.iplseason.analyser.IPLMatchesAnalyzer;

public class IPLAdapterFactory {
    public static <E extends IPLAdapter> E createIPLAdapterObject(IPLMatchesAnalyzer.AdapterType adapterType) {
        if (adapterType.equals(IPLMatchesAnalyzer.AdapterType.RUNADAPTER)) {
            return (E) new IPLMostRunsAdapter();
        } else if (adapterType.equals(IPLMatchesAnalyzer.AdapterType.WICKETADAPTER)) {
            return (E) new IPLMostWicketsAdapter();
        }
        return null;
    }
}
