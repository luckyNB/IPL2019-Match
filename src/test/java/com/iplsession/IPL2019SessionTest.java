package com.iplsession;

import com.iplseason.IPLMatchAnalyserException;
import com.iplseason.analyser.IPLMatchesAnalyzer;
import org.junit.Assert;
import org.junit.Test;

public class IPL2019SessionTest {

    private String IPL_MATCH_RUNS_DATA = "/home/admin1/IPL2019-Match-Analyser/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private String WRONG_IPL_MATCH_DATA = "/home/admin1/IPL2019-Match-Analyser/src/test/resources/WrongIPL2019FactsheetMostRuns.csv";
    private  String EmptyIPL_MATCH_DATA="/home/admin1/IPL2019-Match-Analyser/src/test/resources/EmptyFileIPL2019FactsheetMostRuns.csv";

    @Test
    public void givenIPL2019FactSheetsMostRuns_WhenBindedCorrectly_ThenShould_ReturnTotalRecords() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        int result = 0;
        try {
            result = iplMatchesAnalyzer.loadIplMatchesData(IPL_MATCH_RUNS_DATA);
            Assert.assertEquals(101, result);
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetsMostRuns_WhenWrongDelimeter_ThenShould_ThrowIPLAnalyserException() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        try {
            int result = iplMatchesAnalyzer.loadIplMatchesData(WRONG_IPL_MATCH_DATA);
            Assert.assertEquals(101, result);
        } catch (IPLMatchAnalyserException e) {
        Assert.assertEquals(IPLMatchAnalyserException.ExceptionType.WRONG_DELIMETER_OR_HEADER,e.type);
        }
    }

    @Test
    public void givenIPL2019FactSheetsMostRuns_WhenEmptyFile_ThenShouldThrow_IPLAnalyserException() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        int result = 0;
        try {
            result = iplMatchesAnalyzer.loadIplMatchesData(EmptyIPL_MATCH_DATA);
            Assert.assertEquals(101, result);
        } catch (IPLMatchAnalyserException e) {
            Assert.assertEquals(IPLMatchAnalyserException.ExceptionType.WRONG_DELIMETER_OR_HEADER,e.type);
        }
    }
}
