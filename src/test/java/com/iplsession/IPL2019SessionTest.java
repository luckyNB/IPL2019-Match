package com.iplsession;

import com.iplseason.analyser.IPLMatchesAnalyzer;
import org.junit.Assert;
import org.junit.Test;

public class IPL2019SessionTest {

   private String IPL_MATCH_RUNS_DATA="/home/admin1/IPL2019-Match-Analyser/src/test/resources/IPL2019FactsheetMostRuns.csv";
    @Test
    public void givenIPL2019FactSheetsMostRuns_WhenBindedCorrectly_ThenShould_ReturnTotalRecords() {
        IPLMatchesAnalyzer iplMatchesAnalyzer=new IPLMatchesAnalyzer();
       int result= iplMatchesAnalyzer.loadIplMatchesData(IPL_MATCH_RUNS_DATA);
        Assert.assertEquals(101,result);
    }
}
