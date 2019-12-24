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

import static org.mockito.Mockito.when;

public class MockittoTestOnIPLMostWicketAdapter {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    List<IPLMatchesDAO> dataList = null;
    String filePath = "/home/admin1/IPL2019-Match-Analyser/src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Before
    public void setUp() {
        dataList = new ArrayList<>(Arrays.asList(
                new IPLMatchesDAO("Laxman", 10, 10, 10, 2.3),
                new IPLMatchesDAO("Sachin", 10, 10, 10, 2.3),
                new IPLMatchesDAO("Rahul", 10, 10, 10, 2.3),
                new IPLMatchesDAO("Virat", 10, 10, 10, 2.3),
                new IPLMatchesDAO("Ravindra", 10, 10, 10, 2.3),
                new IPLMatchesDAO("Shoni", 10, 10, 10, 2.3)));
    }

    @Test
    public void givenIPLMostWicketList_WhenCorrect_ShouldReturnList() {
        IPLAdapter iplAdapter = IPLAdapterFactory.createIPLAdapterObject(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER);
        try {
            when(iplAdapter.loadingIPLMatchesData()).thenReturn(dataList);
            IPLMatchesAnalyzer analyzer = new IPLMatchesAnalyzer(iplAdapter);
            List<IPLMatchesDAO> daoList = analyzer.loadIPLMatchesData(filePath);
            //   daoList.forEach(System.out::println);
            Assert.assertEquals(5, daoList.size());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }

    }
}
