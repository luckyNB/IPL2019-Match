package com.iplseason.iplcomparators;

import com.iplseason.analyser.FieldType;

import java.util.Comparator;

public class ComparatorFactory {

    public static <T extends Comparator> T createComparatorObject(FieldType fieldType) {
        if (fieldType.equals(FieldType.AVERAGE)) {
            return (T) new AverageComparator();
        } else if (fieldType.equals(FieldType.STRIKERATE)) {
            return (T) new StrikeRateComparator();
        } else if (fieldType.equals(FieldType.SIXES_AND_FOURS)) {
            return (T) new FoursAndSixesComparator();
        } else if (fieldType.equals(FieldType.RUNS)) {
            return (T) new RunsComparator();
        } else if (fieldType.equals(FieldType.ECONOMY)) {
            return (T) new EconomyComparator();
        } else if (fieldType.equals(FieldType.W5_AND_W4_WICKETS)) {
            return (T) new FourAndFiveWicketComparator();
        } else if (fieldType.equals(FieldType.WICKETS)) {
            return (T) new WicketComparator();
        } else if (fieldType.equals(FieldType.BOWLING_AVERAGE)) {
            return (T) new BowlingAverageComparator();
        }
        return null;
    }

}
