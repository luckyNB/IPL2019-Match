package com.iplsession;

import com.iplseason.IPLMatchAnalyserException;
import com.iplseason.analyser.FieldType;
import com.iplseason.analyser.IPLMatchesAnalyzer;
import com.iplseason.iplcomparators.ComparatorFactory;
import com.iplseason.iplmodel.IplMostRunsData;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

public class IPL2019SessionTest {

    IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
    private String IPL_MATCH_RUNS_DATA = "/home/admin1/IPL2019-Match-Analyser/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private String WRONG_IPL_MATCH_DATA = "/home/admin1/IPL2019-Match-Analyser/src/test/resources/WrongIPL2019FactsheetMostRuns.csv";
    private String EmptyIPL_MATCH_DATA = "/home/admin1/IPL2019-Match-Analyser/src/test/resources/EmptyFileIPL2019FactsheetMostRuns.csv";

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
            Assert.assertEquals(IPLMatchAnalyserException.ExceptionType.WRONG_DELIMETER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIPL2019FactSheetsMostRuns_WhenEmptyFile_ThenShouldThrow_IPLAnalyserException() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        int result = 0;
        try {
            result = iplMatchesAnalyzer.loadIplMatchesData(EmptyIPL_MATCH_DATA);
            Assert.assertEquals(0, result);
        } catch (IPLMatchAnalyserException e) {
            Assert.assertEquals(IPLMatchAnalyserException.ExceptionType.WRONG_DELIMETER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenWrongHeader_ShouldThrowIPLMatchAnalyserException() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        int result = 0;
        try {
            result = iplMatchesAnalyzer.loadIplMatchesData(WRONG_IPL_MATCH_DATA);
            Assert.assertEquals(101, result);
        } catch (IPLMatchAnalyserException e) {
            Assert.assertEquals(IPLMatchAnalyserException.ExceptionType.WRONG_DELIMETER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByAverage_Should_ReturnMS_DhoniAsFirstPlayerInList() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        Comparator comparatorObject = ComparatorFactory.createComparatorObject(FieldType.AVERAGE);
        try {
            iplMatchesAnalyzer.loadIplMatchesData(IPL_MATCH_RUNS_DATA);
            List<IplMostRunsData> list = iplMatchesAnalyzer.getSortedList(comparatorObject);
            Assert.assertEquals("MS Dhoni", list.get(0).playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByAverage_Should_ReturnTimSoutheeAsLarstPlayerInList() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        Comparator comparatorObject = ComparatorFactory.createComparatorObject(FieldType.AVERAGE);
        try {
            iplMatchesAnalyzer.loadIplMatchesData(IPL_MATCH_RUNS_DATA);
            List<IplMostRunsData> list = iplMatchesAnalyzer.getSortedList(comparatorObject);
            Assert.assertEquals("Tim Southee", list.get(list.size() - 1).playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByStrikeRate_Should_ReturnIshantSharmaAsLarstPlayerInList() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        Comparator comparatorObject = ComparatorFactory.createComparatorObject(FieldType.STRIKERATE);
        try {
            iplMatchesAnalyzer.loadIplMatchesData(IPL_MATCH_RUNS_DATA);
            List<IplMostRunsData> list = iplMatchesAnalyzer.getSortedList(comparatorObject);
            Assert.assertEquals("Ishant Sharma", list.get(0).playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByStrikeRate_Should_ReturnPlayerInList() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        Comparator comparatorObject = ComparatorFactory.createComparatorObject(FieldType.STRIKERATE);
        try {
            iplMatchesAnalyzer.loadIplMatchesData(IPL_MATCH_RUNS_DATA);
            List<IplMostRunsData> list = iplMatchesAnalyzer.getSortedList(comparatorObject);
            Assert.assertEquals("Bhuvneshwar Kumar", list.get(list.size() - 1).playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByMost6sAns4s_ThenShould_ReturnTim_Southee_AsLarstPlayerInList() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        Comparator sixexAndFoursComparators = ComparatorFactory.createComparatorObject(FieldType.SIXES_AND_FOURS);
        try {
            iplMatchesAnalyzer.loadIplMatchesData(IPL_MATCH_RUNS_DATA);
            List<IplMostRunsData> list = iplMatchesAnalyzer.getSortedList(sixexAndFoursComparators);
            Assert.assertEquals("Tim Southee", list.get(list.size() - 1).playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByMost6sAns4s_ThenShould_ReturnAndre_Russell_AsFirstPlayerInList() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        Comparator sixexAndFoursComparators = ComparatorFactory.createComparatorObject(FieldType.SIXES_AND_FOURS);

        try {
            iplMatchesAnalyzer.loadIplMatchesData(IPL_MATCH_RUNS_DATA);
            List<IplMostRunsData> list = iplMatchesAnalyzer.getSortedList(sixexAndFoursComparators);
            Assert.assertEquals("Andre Russell", list.get(0).playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByStrikeRateWithSixesAndFours_ThenShould_ReturnBestPlayersList() {
        Comparator strikeRateComparator = ComparatorFactory.createComparatorObject(FieldType.STRIKERATE);
        Comparator sixesAndFoursComparator = ComparatorFactory.createComparatorObject(FieldType.SIXES_AND_FOURS);
        try {
            iplMatchesAnalyzer.loadIplMatchesData(IPL_MATCH_RUNS_DATA);
            List<IplMostRunsData> sortedList = iplMatchesAnalyzer.getSortedList(sixesAndFoursComparator, strikeRateComparator);
            Assert.assertEquals("Andre Russell", sortedList.get(0).playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByStrikeRateWithSixesAndFours_ThenShould_ReturnWorstPlayersList() {
        Comparator strikeRateComparator = ComparatorFactory.createComparatorObject(FieldType.STRIKERATE);
        Comparator sixesAndFoursComparator = ComparatorFactory.createComparatorObject(FieldType.SIXES_AND_FOURS);
        try {
            iplMatchesAnalyzer.loadIplMatchesData(IPL_MATCH_RUNS_DATA);
            List<IplMostRunsData> sortedList = iplMatchesAnalyzer.getSortedList(sixesAndFoursComparator, strikeRateComparator);
            Assert.assertEquals("Shakib Al Hasan", sortedList.get(sortedList.size() - 1).playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }
}
