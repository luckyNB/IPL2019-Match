package com.iplsession;

import com.google.gson.Gson;
import com.iplseason.IPLMatchAnalyserException;
import com.iplseason.analyser.FieldType;
import com.iplseason.analyser.IPLMatchesAnalyzer;
import com.iplseason.iplcomparators.ComparatorFactory;
import com.iplseason.iplmodel.IPLMatchesDAO;
import com.iplseason.iplmodel.IplMostRunsData;
import com.iplseason.iplmodel.IplMostWicketsData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

public class IPL2019SessionTest {

    IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
    Comparator economyComparator, runsComparator, averageComparator, strikeRateComparator, sixesAndFoursComparator;
    private String IPL_WICKETS_DATA = "/home/admin1/IPL2019-Match-Analyser/src/test/resources/IPL2019FactsheetMostWkts.csv";
    private String IPL_MATCH_RUNS_DATA = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private String WRONG_IPL_MATCH_DATA = "./src/test/resources/WrongIPL2019FactsheetMostRuns.csv";
    private String EmptyIPL_MATCH_DATA = "./src/test/resources/EmptyFileIPL2019FactsheetMostRuns.csv";

    @Before
    public void setUp() {
        runsComparator = ComparatorFactory.createComparatorObject(FieldType.RUNS);
        averageComparator = ComparatorFactory.createComparatorObject(FieldType.AVERAGE);
        strikeRateComparator = ComparatorFactory.createComparatorObject(FieldType.STRIKERATE);
        sixesAndFoursComparator = ComparatorFactory.createComparatorObject(FieldType.SIXES_AND_FOURS);
        economyComparator = ComparatorFactory.createComparatorObject(FieldType.ECONOMY);
    }

    @Test
    public void givenIPL2019FactSheetsMostRuns_WhenBindedCorrectly_ThenShould_ReturnTotalRecords() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        int result = 0;
        try {
            List<IPLMatchesDAO> daoList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            Assert.assertEquals(101, daoList.size());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetsMostRuns_WhenWrongDelimeter_ThenShould_ThrowIPLAnalyserException() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        try {
            List<IPLMatchesDAO> daoList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, WRONG_IPL_MATCH_DATA);
            Assert.assertEquals(101, daoList.size());
        } catch (IPLMatchAnalyserException e) {
            Assert.assertEquals(IPLMatchAnalyserException.ExceptionType.WRONG_DELIMETER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIPL2019FactSheetsMostRuns_WhenEmptyFile_ThenShouldThrow_IPLAnalyserException() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        int result = 0;
        try {
            List<IPLMatchesDAO> daoList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, EmptyIPL_MATCH_DATA);
            Assert.assertEquals(0, daoList.size());
        } catch (IPLMatchAnalyserException e) {
            Assert.assertEquals(IPLMatchAnalyserException.ExceptionType.WRONG_DELIMETER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenWrongHeader_ShouldThrowIPLMatchAnalyserException() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        int result = 0;
        try {
            List<IPLMatchesDAO> iplMatchesDAOS = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, WRONG_IPL_MATCH_DATA);
            Assert.assertEquals(101, iplMatchesDAOS.size());
        } catch (IPLMatchAnalyserException e) {
            Assert.assertEquals(IPLMatchAnalyserException.ExceptionType.WRONG_DELIMETER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByAverage_Should_ReturnBestPlayerInList() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        Comparator comparatorObject = ComparatorFactory.createComparatorObject(FieldType.AVERAGE);
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(comparatorObject);
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("MS Dhoni", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByAverage_Should_ReturnWorstPlayerInList() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        Comparator comparatorObject = ComparatorFactory.createComparatorObject(FieldType.AVERAGE);
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(comparatorObject);
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Tim Southee", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByStrikeRate_Should_ReturnBestPlayerInList() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        Comparator comparatorObject = ComparatorFactory.createComparatorObject(FieldType.STRIKERATE);
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(comparatorObject);
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Ishant Sharma", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByStrikeRate_Should_ReturnWorstPlayerInList() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        Comparator comparatorObject = ComparatorFactory.createComparatorObject(FieldType.STRIKERATE);
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(comparatorObject);
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Bhuvneshwar Kumar", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByMost6sAns4s_ThenShould_ReturnTim_Southee_AsLarstPlayerInList() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        Comparator sixexAndFoursComparators = ComparatorFactory.createComparatorObject(FieldType.SIXES_AND_FOURS);
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(sixexAndFoursComparators);
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Tim Southee", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByMost6sAns4s_ThenShould_ReturnAndre_Russell_AsFirstPlayerInList() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        Comparator sixexAndFoursComparators = ComparatorFactory.createComparatorObject(FieldType.SIXES_AND_FOURS);
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(sixexAndFoursComparators);
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Andre Russell", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByStrikeRateWithSixesAndFours_ThenShould_ReturnBestPlayersList() {
        Comparator strikeRateComparator = ComparatorFactory.createComparatorObject(FieldType.STRIKERATE);
        Comparator sixesAndFoursComparator = ComparatorFactory.createComparatorObject(FieldType.SIXES_AND_FOURS);
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(sixesAndFoursComparator, strikeRateComparator);
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Andre Russell", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByStrikeRateWithSixesAndFours_ThenShould_ReturnWorstPlayersList() {
        Comparator strikeRateComparator = ComparatorFactory.createComparatorObject(FieldType.STRIKERATE);
        Comparator sixesAndFoursComparator = ComparatorFactory.createComparatorObject(FieldType.SIXES_AND_FOURS);
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(sixesAndFoursComparator, strikeRateComparator);
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Shakib Al Hasan", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByAverageWithStrikeRates_ThenShould_ReturnBestPlayersList() {
        try {
            Comparator strikeRateComparator = ComparatorFactory.createComparatorObject(FieldType.STRIKERATE);
            Comparator averageRateComparator = ComparatorFactory.createComparatorObject(FieldType.AVERAGE);
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(averageRateComparator, strikeRateComparator);
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("MS Dhoni", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByAverageWithStrikeRates_ThenShould_ReturnWorstPlayersList() {
        Comparator strikeRateComparator = ComparatorFactory.createComparatorObject(FieldType.STRIKERATE);
        Comparator averageRateComparator = ComparatorFactory.createComparatorObject(FieldType.AVERAGE);

        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(averageRateComparator, strikeRateComparator);
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Tim Southee", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByMaxRunsWithBestAverage_ThenShould_ReturnBestPlayersList() {
        Comparator runsComparator = ComparatorFactory.createComparatorObject(FieldType.RUNS);
        Comparator averageComparator = ComparatorFactory.createComparatorObject(FieldType.AVERAGE);
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(runsComparator, averageComparator);
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("David Warner", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByMaxRunsWithBestAverage_ThenShould_ReturnWorstPlayersList() {
        Comparator runsComparator = ComparatorFactory.createComparatorObject(FieldType.RUNS);
        Comparator averageComparator = ComparatorFactory.createComparatorObject(FieldType.AVERAGE);
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(runsComparator.reversed(), averageComparator.reversed());
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Tim Southee", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_ThenShouldReturn_NumberOfRecord() {
        try {
            List<IPLMatchesDAO> daoList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            Assert.assertEquals(101, daoList.size());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByBowlingAvg_ShouldReturnBestPlayerInList() {
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            String sortedList1 = iplMatchesAnalyzer.getSortedList(averageComparator);
            IplMostWicketsData[] sortedList = new Gson().fromJson(sortedList1, IplMostWicketsData[].class);
            Assert.assertEquals("Krishnappa Gowtham", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByBowlingAvg_ShouldWorstPlayerInList() {
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            String sortedList1 = iplMatchesAnalyzer.getSortedList(averageComparator);
            IplMostWicketsData[] sortedList = new Gson().fromJson(sortedList1, IplMostWicketsData[].class);
            Assert.assertEquals("Liam Livingstone", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByStrikeRate_ShouldBestPlayerInList() {
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            String sortedList1 = iplMatchesAnalyzer.getSortedList(strikeRateComparator);
            IplMostWicketsData[] sortedList = new Gson().fromJson(sortedList1, IplMostWicketsData[].class);
            Assert.assertEquals("Krishnappa Gowtham", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByStrikeRate_ShouldWorstPlayerInList() {
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            String sortedList1 = iplMatchesAnalyzer.getSortedList(strikeRateComparator);
            IplMostWicketsData[] sortedList = new Gson().fromJson(sortedList1, IplMostWicketsData[].class);
            Assert.assertEquals("Liam Livingstone", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

}
