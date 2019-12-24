package com.iplsession;

import com.iplseason.IPLMatchAnalyserException;
import com.iplseason.analyser.IPLMatchesAnalyzer;
import com.iplseason.ipladapters.IPLAdapter;
import com.iplseason.ipladapters.IPLAdapterFactory;
import com.iplseason.iplmodel.IPLMatchesDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPLMostRunsAdapterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    IPLAdapter iplAdapter = IPLAdapterFactory.createIPLAdapterObject(IPLMatchesAnalyzer.PlayerType.RUNADAPTER);
    List<IPLMatchesDAO> dataList = null;
    String differntType = "./src/test/resources/IPLDifferentType.json";
    private String IPL_WICKETS_DATA = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private String IPL_MATCH_RUNS_DATA = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private String WRONG_IPL_MATCH_DATA = "./src/test/resources/WrongIPL2019FactsheetMostRuns.csv";
    private String EmptyIPL_MATCH_DATA = "./src/test/resources/EmptyFileIPL2019FactsheetMostRuns.csv";

    @Before
    public void setUp() {
        dataList = new ArrayList<>(Arrays
                .asList(new IPLMatchesDAO("Laxman", 10, 12.23, 10, 10, 23.3),
                        new IPLMatchesDAO("Prafull", 20, 22.23, 10, 10, 23.3),
                        new IPLMatchesDAO("Vaibhav", 30, 32.23, 10, 10, 23.3),
                        new IPLMatchesDAO("Krishna", 40, 42.23, 10, 10, 23.3),
                        new IPLMatchesDAO("Kartavya", 50, 52.23, 10, 10, 23.3)));
    }

    @Test
    public void givenEmptyIPLMostRunsData_WhenEmptyFile_ShouldReturn_Zero() {
        IPLAdapter iplAdapter2 = mock(iplAdapter.getClass());
        try {
            List<IPLMatchesDAO> daoList = iplAdapter2.loadingIPLMatchesData(EmptyIPL_MATCH_DATA);
            Assert.assertEquals(0, daoList.size());
        } catch (IPLMatchAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsData_WhenFileHasWrongDelimter_ShouldReturn_IPLMatchesAnalyserException() {
        IPLAdapter iplAdapter2 = mock(iplAdapter.getClass());
        try {
            List<IPLMatchesDAO> daoList = iplAdapter2.loadingIPLMatchesData(WRONG_IPL_MATCH_DATA);
            Assert.assertEquals(100, daoList.size());
        } catch (IPLMatchAnalyserException e) {
            Assert.assertEquals(IPLMatchAnalyserException.ExceptionType.WRONG_DELIMETER_OR_HEADER, e.type);

        }
    }

    @Test
    public void givenIPLMostRunsData_WhenFileHasWrongHeader_ShouldReturn_IPLMatchesAnalyserException() {
        try {
            List<IPLMatchesDAO> daoList = iplAdapter.loadingIPLMatchesData(WRONG_IPL_MATCH_DATA);
            Assert.assertEquals(100, daoList.size());
        } catch (IPLMatchAnalyserException e) {
            Assert.assertEquals(IPLMatchAnalyserException.ExceptionType.WRONG_DELIMETER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_WhenFileHasDifferentType_ShouldReturn_IPLMatchesAnalyserException() {
        try {
            List<IPLMatchesDAO> daoList = iplAdapter.loadingIPLMatchesData(differntType);
            Assert.assertEquals(100, daoList.size());
        } catch (IPLMatchAnalyserException e) {
            Assert.assertEquals(IPLMatchAnalyserException.ExceptionType.WRONG_DELIMETER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIPLMostRunsCSV_WhenMocked_ShouldReturnDummyListSize() {
        IPLAdapter iplAdapterMock = mock(iplAdapter.getClass());
        try {
            when(iplAdapterMock.loadingIPLMatchesData(IPL_MATCH_RUNS_DATA)).thenReturn(dataList);
            List<IPLMatchesDAO> daoList = iplAdapterMock.loadingIPLMatchesData(IPL_MATCH_RUNS_DATA);
            Assert.assertEquals(5, daoList.size());
        } catch (IPLMatchAnalyserException e) {
        }
    }
}
