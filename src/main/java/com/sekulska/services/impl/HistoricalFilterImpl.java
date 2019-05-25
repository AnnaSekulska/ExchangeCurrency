package com.sekulska.services.impl;

import com.sekulska.datacheck.PriceData;
import com.sekulska.exceptions.RangeExceededException;
import com.sekulska.services.HistoricalFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoricalFilterImpl implements HistoricalFilter {

    public List<PriceData> filter(List<PriceData> input, int range, int step){
        if(input.size() >= range){
            List<PriceData> filteredPriceData = new ArrayList<>();
            for (int i =  input.size() - 1; i >= input.size() - range; i -= step){
                filteredPriceData.add(input.get(i));
            }
            return filteredPriceData;
        }
        throw new RangeExceededException("No data for the given time interval.");
    }
}
