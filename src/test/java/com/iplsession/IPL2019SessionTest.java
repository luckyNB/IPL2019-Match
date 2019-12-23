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
    Comparator economyComparator, runsComparator, averageComparator, strikeRateComparator, sixesAndFoursComparator, w4And5w_wicketComparator, wicketComparator;
    String dummyRunsData = "./src/test/resources/DummyBattingCSV.csv";
    String dummyBowlingData = "./src/test/resources/DummyBowlingCSV.csv";
    private String IPL_WICKETS_DATA = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
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
        w4And5w_wicketComparator = ComparatorFactory.createComparatorObject(FieldType.W5_AND_W4_WICKETS);
        wicketComparator = ComparatorFactory.createComparatorObject(FieldType.WICKETS);
    }

    @Test
    public void givenIPL2019FactSheetsMostRuns_WhenBindedCorrectly_ThenShould_ReturnTotalRecords() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();

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

        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(averageComparator.reversed());
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("MS Dhoni", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByAverage_Should_ReturnWorstPlayerInList() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(averageComparator.reversed());
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Tim Southee", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByStrikeRate_Should_ReturnBestPlayerInList() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(strikeRateComparator.reversed());
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
            String list = iplMatchesAnalyzer.getSortedList(strikeRateComparator.reversed());
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Bhuvneshwar Kumar", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByMost6sAns4s_ThenShould_ReturnWorstPlayerInList() {
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(sixesAndFoursComparator.reversed());
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Tim Southee", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByMost6sAns4s_ThenShould_ReturnAndre_Russell_AsFirstPlayerInList() {
        IPLMatchesAnalyzer iplMatchesAnalyzer = new IPLMatchesAnalyzer();
        //  Comparator sixexAndFoursComparators = ComparatorFactory.createComparatorObject(FieldType.SIXES_AND_FOURS);
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(sixesAndFoursComparator.reversed());
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Andre Russell", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByStrikeRateWithSixesAndFours_ThenShould_ReturnBestPlayersList() {
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, dummyRunsData);
            String list = iplMatchesAnalyzer.getSortedList(sixesAndFoursComparator.reversed(), strikeRateComparator.reversed());
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Andre Russell", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByStrikeRateWithSixesAndFours_ThenShould_ReturnWorstPlayersList() {

        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(sixesAndFoursComparator.reversed(), strikeRateComparator.reversed());
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Shakib Al Hasan", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByAverageWithStrikeRates_ThenShould_ReturnBestPlayersList() {
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(averageComparator.reversed(), strikeRateComparator.reversed());
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("MS Dhoni", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByAverageWithStrikeRates_ThenShould_ReturnWorstPlayersList() {
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(averageComparator.reversed(), strikeRateComparator.reversed());
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Tim Southee", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByMaxRunsWithBestAverage_ThenShould_ReturnBestPlayersList() {
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(runsComparator.reversed(), averageComparator.reversed());
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("David Warner", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactSheetMostRuns_WhenSortedByMaxRunsWithBestAverage_ThenShould_ReturnWorstPlayersList() {
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            String list = iplMatchesAnalyzer.getSortedList(runsComparator.reversed(), averageComparator.reversed());
            IplMostRunsData[] sortedList = new Gson().fromJson(list, IplMostRunsData[].class);
            Assert.assertEquals("Tim Southee", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_ThenShouldReturn_NumberOfRecord() {
        try {
            List<IPLMatchesDAO> daoList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            Assert.assertEquals(99, daoList.size());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByBowlingAvg_ShouldReturnBestPlayerInList() {
        try {
            iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            String sortedList1 = iplMatchesAnalyzer.getSortedList(averageComparator.reversed());
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
            String sortedList1 = iplMatchesAnalyzer.getSortedList(averageComparator.reversed());
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
            String sortedList1 = iplMatchesAnalyzer.getSortedList(strikeRateComparator.reversed());
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
            String sortedList1 = iplMatchesAnalyzer.getSortedList(strikeRateComparator.reversed());
            IplMostWicketsData[] sortedList = new Gson().fromJson(sortedList1, IplMostWicketsData[].class);
            Assert.assertEquals("Liam Livingstone", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByEconomy_ShouldBestPlayerInList() {
        try {
            List<IPLMatchesDAO> daoList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            String sortedList1 = iplMatchesAnalyzer.getSortedList(economyComparator);
            IplMostWicketsData[] sortedList = new Gson().fromJson(sortedList1, IplMostWicketsData[].class);
            Assert.assertEquals("Anukul Roy", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByEconomy_ShouldWorstPlayerInList() {
        try {
            List<IPLMatchesDAO> daoList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            String sortedList1 = iplMatchesAnalyzer.getSortedList(economyComparator);
            IplMostWicketsData[] sortedList = new Gson().fromJson(sortedList1, IplMostWicketsData[].class);
            Assert.assertEquals("Liam Livingstone", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByStrikeRateWith4wAnd5w_ShouldBestPlayerInList() {
        try {
            List<IPLMatchesDAO> daoList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            String sortedList1 = iplMatchesAnalyzer.getSortedList(strikeRateComparator.reversed(), w4And5w_wicketComparator.reversed());
            IplMostWicketsData[] sortedList = new Gson().fromJson(sortedList1, IplMostWicketsData[].class);
            Assert.assertEquals("Krishnappa Gowtham", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByStrikeRateWith4wAnd5w_ShouldWorstPlayerInList() {
        try {
            List<IPLMatchesDAO> daoList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            String sortedList1 = iplMatchesAnalyzer.getSortedList(strikeRateComparator.reversed(), w4And5w_wicketComparator.reversed());
            IplMostWicketsData[] sortedList = new Gson().fromJson(sortedList1, IplMostWicketsData[].class);
            Assert.assertEquals("Liam Livingstone", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByBowlingAverageAndStrikeRate_ShouldBestPlayerInList() {
        try {
            List<IPLMatchesDAO> daoList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            String sortedList1 = iplMatchesAnalyzer.getSortedList(strikeRateComparator.reversed(), averageComparator.reversed());
            IplMostWicketsData[] sortedList = new Gson().fromJson(sortedList1, IplMostWicketsData[].class);
            Assert.assertEquals("Krishnappa Gowtham", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByBowlingAverageAndStrikeRate_ShouldWorstPlayerInList() {
        try {
            List<IPLMatchesDAO> daoList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            String sortedList1 = iplMatchesAnalyzer.getSortedList(strikeRateComparator.reversed(), averageComparator.reversed());
            IplMostWicketsData[] sortedList = new Gson().fromJson(sortedList1, IplMostWicketsData[].class);
            Assert.assertEquals("Liam Livingstone", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByMaxWicketsAndBestAndBestBowlingAverage_ShouldBestPlayerInList() {
        try {
            List<IPLMatchesDAO> daoList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            String sortedList1 = iplMatchesAnalyzer.getSortedList(wicketComparator.reversed(), averageComparator.reversed());
            IplMostWicketsData[] sortedList = new Gson().fromJson(sortedList1, IplMostWicketsData[].class);
            Assert.assertEquals("Imran Tahir", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByMaxWicketsAndBestAndBestBowlingAverage_ShouldWorstPlayerInList() {
        try {
            List<IPLMatchesDAO> daoList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            String sortedList1 = iplMatchesAnalyzer.getSortedList(wicketComparator.reversed(), averageComparator.reversed());
            IplMostWicketsData[] sortedList = new Gson().fromJson(sortedList1, IplMostWicketsData[].class);
            Assert.assertEquals("Liam Livingstone", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByBestBattingAndBestBowlingAverage_ShouldBestPlayerInList() {
        try {
            List<IPLMatchesDAO> runsList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            List<IPLMatchesDAO> wicketList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            String resultList = iplMatchesAnalyzer.mergingList(runsList, wicketList);
            IPLMatchesDAO[] sortedList = new Gson().fromJson(resultList, IPLMatchesDAO[].class);
            Assert.assertEquals("Krishnappa Gowtham", sortedList[0].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByBestBattingAndBestBowlingAverage_ShouldWorstPlayerInList() {
        try {
            List<IPLMatchesDAO> runsList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            List<IPLMatchesDAO> wicketList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            String resultList = iplMatchesAnalyzer.mergingList(runsList, wicketList);
            IPLMatchesDAO[] sortedList = new Gson().fromJson(resultList, IPLMatchesDAO[].class);
            Assert.assertEquals("Harpreet Brar", sortedList[sortedList.length - 1].playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByMaxRunsAndMaxWickets_ShouldBestPlayerInList() {
        try {
            List<IPLMatchesDAO> mostRunsList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            List<IPLMatchesDAO> mostWicketList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            List<IPLMatchesDAO> sortedRunsListWithComparator = iplMatchesAnalyzer.getSortedListWithComparator(mostRunsList, runsComparator);
            List<IPLMatchesDAO> sortedWicketListWithComparator = iplMatchesAnalyzer.getSortedListWithComparator(mostWicketList, wicketComparator);
            IPLMatchesDAO allRounerPlayer = iplMatchesAnalyzer.compareTwoListsAndGetAllRounder(sortedRunsListWithComparator, sortedWicketListWithComparator);
            Assert.assertEquals("Andre Russell", allRounerPlayer.playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019FactsSheetMostWickets_WhenSortedByMaxRunsAndMaxWickets_ShouldWorstPlayerInList() {
        try {
            List<IPLMatchesDAO> mostRunsList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.RUNADAPTER, IPL_MATCH_RUNS_DATA);
            List<IPLMatchesDAO> mostWicketList = iplMatchesAnalyzer.loadIPLMatchecData(IPLMatchesAnalyzer.PlayerType.WICKETADAPTER, IPL_WICKETS_DATA);
            List<IPLMatchesDAO> sortedRunsListWithComparator = iplMatchesAnalyzer.getSortedListWithComparator(mostRunsList, runsComparator.reversed());
            List<IPLMatchesDAO> sortedWicketListWithComparator = iplMatchesAnalyzer.getSortedListWithComparator(mostWicketList, wicketComparator.reversed());
            IPLMatchesDAO allRounerPlayer = iplMatchesAnalyzer.compareTwoListsAndGetAllRounder(sortedRunsListWithComparator, sortedWicketListWithComparator);
            Assert.assertEquals("Shakib Al Hasan", allRounerPlayer.playerName.trim());
        } catch (IPLMatchAnalyserException e) {
            e.printStackTrace();
        }
    }
}
