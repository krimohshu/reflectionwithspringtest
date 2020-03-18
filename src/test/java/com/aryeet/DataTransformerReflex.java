package com.aryeet;

import java.util.List;
import java.util.Map;

public class DataTransformerReflex {

    private static Integer leftSideValue = 0;
    //   private static Integer RightSideValue=0;
    private Integer valueToPrint = 0;

    public Long squareOfInteger(Integer currentVal) {
        return currentVal * currentVal * 1L;
    }

    public Integer numDivByThree(Integer currentVal) {
        return currentVal % 3 == 0 ? currentVal : null;
    }

    public Integer addingAdjacentNumber(Integer currentVal, List<Integer> originalList) {
        AdjacentMapper adjacentMapper = new AdjacentMapper();
        AdjacentMapper adjacentMapperTransformed = new AdjacentMapper();

        Map<String, AdjacentMapper> adjacentMapperMap = adjacentMapper.createGraph(originalList);

        adjacentMapperTransformed = adjacentMapperMap.entrySet().stream()
                .filter(x -> x.getKey().equalsIgnoreCase(String.valueOf(currentVal)))
                .findFirst().get().getValue();

        return adjacentMapperTransformed.getLeftElement() + adjacentMapperTransformed.getCurrentElement() + adjacentMapperTransformed.getRightElement();

    }
}
