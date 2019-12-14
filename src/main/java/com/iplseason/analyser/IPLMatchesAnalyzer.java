package com.iplseason.analyser;

import com.bridgelabz.CSVBuilderException;
import com.bridgelabz.CSVBuilderFactory;
import com.bridgelabz.ICSVBuilder;
import com.iplseason.IPLMatchAnalyserException;
import com.iplseason.iplmodel.IplMostRunsData;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IPLMatchesAnalyzer {
    public int loadIplMatchesData(String ipl_match_runs_data) throws IPLMatchAnalyserException {
        Map<Integer, IplMostRunsData> mostRunsDataMap = new HashMap<>();
        ICSVBuilder builder = CSVBuilderFactory.createCSVBuilder();
        try (Reader reader = Files.newBufferedReader(Paths.get(ipl_match_runs_data))) {
            Iterator<IplMostRunsData> csvFileIterator = builder.getCSVFileIterator(reader, IplMostRunsData.class);
            Iterable<IplMostRunsData> csvFileIterable = () -> csvFileIterator;
            int count = (int) StreamSupport.stream(csvFileIterable.spliterator(), false).count();
            return count;
        } catch (IOException e) {
        throw  new IPLMatchAnalyserException("Error While Reading file",IPLMatchAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (CSVBuilderException e) {
            throw new  IPLMatchAnalyserException("Error While Binding CSV file",IPLMatchAnalyserException.ExceptionType.CSV_BIND_ERROR);
        }
        catch (RuntimeException e){
          throw  new IPLMatchAnalyserException("given delimeter or header",IPLMatchAnalyserException.ExceptionType.WRONG_DELIMETER_OR_HEADER)  ;
        }
    }
}
