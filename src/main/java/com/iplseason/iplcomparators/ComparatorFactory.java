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
        }
        return null;
    }
}
