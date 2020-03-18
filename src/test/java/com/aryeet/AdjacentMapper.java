package com.aryeet;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AdjacentMapper{

    private String keyElement;
    private Integer leftElement;
    private Integer currentElement;
    private Integer rightElement;

    Map<String, AdjacentMapper> adjacentMapperMap = new LinkedHashMap<>();


    protected Map<String, AdjacentMapper> createGraph(List<Integer> OriginalList) {
        int count=0;

        for (int i = 0; i < OriginalList.size() - 1; i++) {
            AdjacentMapper am = new AdjacentMapper();
            am.setKeyElement(String.valueOf(OriginalList.get(i)));

            am.setCurrentElement(OriginalList.get(i));
            if(i==0)
                am.setLeftElement(0);
            else
                am.setLeftElement(OriginalList.get(i-1));

            if(count >= OriginalList.size() - 1)
                 am.setRightElement(0);
            else
                am.setRightElement(OriginalList.get(i+1));



            adjacentMapperMap.put(String.valueOf(OriginalList.get(i)) , am);
            count++;


        }
        return adjacentMapperMap;
    }


    public String getKeyElement() {
        return keyElement;
    }

    public void setKeyElement(String keyElement) {
        this.keyElement = keyElement;
    }

    public Integer getLeftElement() {
        return leftElement;
    }

    public void setLeftElement(Integer leftElement) {
        this.leftElement = leftElement;
    }

    public Integer getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(Integer currentElement) {
        this.currentElement = currentElement;
    }

    public Integer getRightElement() {
        return rightElement;
    }

    public void setRightElement(Integer rightElement) {
        this.rightElement = rightElement;
    }

    public Map<String, AdjacentMapper> getAdjacentMapperMap() {
        return adjacentMapperMap;
    }

    public void setAdjacentMapperMap(Map<String, AdjacentMapper> adjacentMapperMap) {
        this.adjacentMapperMap = adjacentMapperMap;
    }

    @Override
    public String toString() {
        return "AdjacentMapper{" +
                "keyElement='" + keyElement + '\'' +
                ", leftElement=" + leftElement +
                ", currentElement=" + currentElement +
                ", rightElement=" + rightElement +
                '}';
    }
}
