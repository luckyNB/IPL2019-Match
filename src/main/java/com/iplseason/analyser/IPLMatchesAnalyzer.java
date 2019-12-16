package com.iplseason.analyser;

import com.bridgelabz.CSVBuilderException;
import com.bridgelabz.CSVBuilderFactory;
import com.bridgelabz.ICSVBuilder;
import com.iplseason.IPLMatchAnalyserException;
import com.iplseason.iplcomparators.GroupBySorter;
import com.iplseason.iplmodel.IplMostRunsData;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class IPLMatchesAnalyzer {

    List<IplMostRunsData> iplRunsList = null;
    Map<FieldType, Comparator> comparatorMap = null;
    Comparator<IplMostRunsData> iplMostRunsDataComparator = null;

    public IPLMatchesAnalyzer() {
        iplRunsList = new ArrayList<>();
        comparatorMap = new HashMap<>();
        comparatorMap.put(FieldType.AVERAGE, (iplMostRunsDataComparator = Comparator.comparing(iplMostRunsData -> iplMostRunsData.average)));
        comparatorMap.put(FieldType.STRIKERATE, iplMostRunsDataComparator = Comparator.comparing(iplMostRunsData -> iplMostRunsData.strikeRate));

    }

    public int loadIplMatchesData(String ipl_match_runs_data) throws IPLMatchAnalyserException {

        ICSVBuilder builder = CSVBuilderFactory.createCSVBuilder();
        try (Reader reader = Files.newBufferedReader(Paths.get(ipl_match_runs_data))) {
            Iterator<IplMostRunsData> csvFileIterator = builder.getCSVFileIterator(reader, IplMostRunsData.class);
            Iterable<IplMostRunsData> csvFileIterable = () -> csvFileIterator;
            StreamSupport.stream(csvFileIterable.spliterator(), false)
                    .forEach(iplMostRunsData -> iplRunsList.add(iplMostRunsData));

            int count = iplRunsList.size();
            return count;
        } catch (IOException e) {
            throw new IPLMatchAnalyserException("Error While Reading file", IPLMatchAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (CSVBuilderException e) {
            throw new IPLMatchAnalyserException("Error While Binding CSV file", IPLMatchAnalyserException.ExceptionType.CSV_BIND_ERROR);
        } catch (RuntimeException e) {
            throw new IPLMatchAnalyserException("given delimeter or header", IPLMatchAnalyserException.ExceptionType.WRONG_DELIMETER_OR_HEADER);
        }
    }

    public List<IplMostRunsData> getSortedList(Comparator<IplMostRunsData>... comparatorsList) {
        if (comparatorsList.length == 1) {
            Collections.sort(iplRunsList, new GroupBySorter(comparatorsList[0]));
            return iplRunsList;
        } else if (comparatorsList.length == 2) {
            Collections.sort(iplRunsList, new GroupBySorter(comparatorsList[0], comparatorsList[1]));
            return iplRunsList;
        }

        return null;
    }
}
