package com.iplsession;

import com.iplseason.IPLMatchAnalyserException;
import com.iplseason.analyser.IPLMatchesAnalyzer;
import com.iplseason.ipladapters.IPLAdapter;
import com.iplseason.ipladapters.IPLMostRunsAdapter;
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

public class MockitoIPLMostRunsTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    List<IPLMatchesDAO> dataList = null;
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
    public void givenIPLMatchesRunsData_WhenCorrect_ShouldReturnList() {
        try {
            IPLMatchesAnalyzer iplMatchesAnalyzer = mock(IPLMatchesAnalyzer.class);
            setUp();
            when(iplMatchesAnalyzer.loadIPLMatchesData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA)).thenReturn(dataList);
            List<IPLMatchesDAO> daoList = iplMatchesAnalyzer.loadIPLMatchesData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            daoList.forEach(System.out::println);
            Assert.assertEquals(5, daoList.size());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRunsData_WhenCorrect_ShouldReturnList() {

        IPLAdapter iplMostRunsAdapter = mock(IPLMostRunsAdapter.class);
        try {
            when(iplMostRunsAdapter.loadingIPLMatchesData(IPL_MATCH_RUNS_DATA)).thenReturn(dataList);
            List<IPLMatchesDAO> matchesDAOList = iplMostRunsAdapter.loadingIPLMatchesData(IPL_MATCH_RUNS_DATA);
            matchesDAOList.forEach(System.out::println);
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }

    }
}
