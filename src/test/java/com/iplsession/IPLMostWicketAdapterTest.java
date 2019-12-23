package com.iplsession;

import com.iplseason.IPLMatchAnalyserException;
import com.iplseason.analyser.IPLMatchesAnalyzer;
import com.iplseason.ipladapters.IPLAdapter;
import com.iplseason.ipladapters.IPLAdapterFactory;
import com.iplseason.iplmodel.IPLMatchesDAO;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IPLMostWicketAdapterTest {
    IPLAdapter iplMostWicketAdapter = IPLAdapterFactory.createIPLAdapterObject(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER);
    private String IPL_WICKETS_DATA = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private String IPL_MATCH_RUNS_DATA = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private String WRONG_IPL_MATCH_DATA = "./src/test/resources/WrongIPL2019FactsheetMostRuns.csv";
    private String EmptyIPL_MATCH_DATA = "./src/test/resources/EmptyFileIPL2019FactsheetMostRuns.csv";

    @Test
    public void givenIPLMatchesWicketData_WhenEmpty_File_ShouldReturnZero() {
        try {
            List<IPLMatchesDAO> daoList = iplMostWicketAdapter.loadingIPLMatchesData(EmptyIPL_MATCH_DATA);
            Assert.assertEquals(0, daoList.size());
        } catch (IPLMatchAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMatchesWicketData_WhenWrongDelimeter_ShouldReturnIPLMatchecsAnalyserException() {
        try {
            List<IPLMatchesDAO> daoList = iplMostWicketAdapter.loadingIPLMatchesData(WRONG_IPL_MATCH_DATA);
            Assert.assertEquals(100, daoList.size());
        } catch (IPLMatchAnalyserException e) {
            Assert.assertEquals(IPLMatchAnalyserException.ExceptionType.WRONG_DELIMETER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIPLMatchesWicketData_WhenWrongHeader_ShouldReturnIPLMatchecsAnalyserException() {
        try {
            List<IPLMatchesDAO> daoList = iplMostWicketAdapter.loadingIPLMatchesData(WRONG_IPL_MATCH_DATA);
            Assert.assertEquals(100, daoList.size());
        } catch (IPLMatchAnalyserException e) {
            Assert.assertEquals(IPLMatchAnalyserException.ExceptionType.WRONG_DELIMETER_OR_HEADER, e.type);
        }
    }
}
