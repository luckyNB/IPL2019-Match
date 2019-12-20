package com.iplseason.ipladapters;

import com.iplseason.analyser.IPLMatchesAnalyzer;

public class IPLAdapterFactory {
    public static <E extends IPLAdapter> E createIPLAdapterObject(IPLMatchesAnalyzer.PlayerType adapterType) {
        if (adapterType.equals(IPLMatchesAnalyzer.PlayerType.RUNADAPTER)) {
            return (E) new IPLMostRunsAdapter();
        } else if (adapterType.equals(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER)) {
            return (E) new IPLMostWicketsAdapter();
        }
        return null;
    }
}
