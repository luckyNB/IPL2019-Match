package com.iplseason.ipladapters;

import com.bridgelabz.CSVBuilderException;
import com.bridgelabz.CSVBuilderFactory;
import com.bridgelabz.ICSVBuilder;
import com.iplseason.IPLMatchAnalyserException;
import com.iplseason.iplmodel.IPLMatchesDAO;
import com.iplseason.iplmodel.IplMostRunsData;
import com.iplseason.iplmodel.IplMostWicketsData;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public abstract class IPLAdapter {
    public abstract List<IPLMatchesDAO> loadingIPLMatchesData(String... csvFilePath) throws IPLMatchAnalyserException;

    protected <E> List<IPLMatchesDAO> loadingIPLMatchesData(Class<E> censusCSVClass, String csvFilePath) throws IPLMatchAnalyserException {
        List<IPLMatchesDAO> daoList = new ArrayList<>();
        Iterator<E> csvFileIterator = null;
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            try {
                csvFileIterator = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
                Iterator<E> finalCsvFileIterator = csvFileIterator;
                Iterable<E> csvIterable = () -> finalCsvFileIterator;
                if (censusCSVClass.getName().equals("com.iplseason.iplmodel.IplMostRunsData")) {
                    StreamSupport.stream
                            (csvIterable.spliterator(), false)
                            .map(IplMostRunsData.class::cast)
                            .forEach(censusCSV -> daoList.add(new IPLMatchesDAO(censusCSV)));
                } else if (censusCSVClass.getName().equals("com.iplseason.iplmodel.IplMostWicketsData")) {
                    StreamSupport.stream
                            (csvIterable.spliterator(), false)
                            .map(IplMostWicketsData.class::cast)
                            .forEach(censusCSV -> daoList.add(new IPLMatchesDAO(censusCSV)));
                }
            } catch (CSVBuilderException e) {
                throw new IPLMatchAnalyserException("Error in CSV file Binding", IPLMatchAnalyserException.ExceptionType.CSV_BIND_ERROR);
            }
        } catch (RuntimeException e) {
            throw new IPLMatchAnalyserException("Error in format delimter or header wrong", IPLMatchAnalyserException.ExceptionType.WRONG_DELIMETER_OR_HEADER);
        } catch (IOException e) {
            throw new IPLMatchAnalyserException("Error while reading file", IPLMatchAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        return daoList;
    }
}
