package com.sekulska.services.impl;

import com.sekulska.datacheck.PriceData;
import com.sekulska.model.TrendLineInfo;
import com.sekulska.services.TrendLineDataSelector;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class TrendLineDataSelectorImpl implements TrendLineDataSelector {

    @Override
    public List<TrendLineInfo> getTrendLineInfo(List<PriceData> priceData, int range) {
        int windowSize = getWindowSize(range);
        if(windowSize > priceData.size())
            throw new IllegalArgumentException("Please select smaller window size. Proposed size is: " + priceData.size()/3 + "or smaller.");
        if(windowSize < 1)
            throw new IllegalArgumentException("Please select wider window size. Proposed size is between: " + priceData.size()/3 + "and 2." );

        return getTrends(priceData, windowSize);
    }


    List<TrendLineInfo> getTrends(List<PriceData> priceData, int windowSize){
        List<TrendLineInfo> result = new ArrayList<>();
        List<PriceData> sublist;
        PriceData currentMax;
        boolean isDownTrend = false;
        boolean isUpTrend = false;
        PriceData previousMax = Collections.max(priceData.subList(0, windowSize), Comparator.comparing(PriceData::getPrice));
        PriceData startMax = previousMax;

        for(int i = windowSize ; i < priceData.size() ; i += windowSize){

            if(i + windowSize <= priceData.size()){
                sublist = priceData.subList(i, i + windowSize);
                currentMax = Collections.max(sublist, Comparator.comparing(PriceData::getPrice));
            }else {
               if(isUpTrend) result.add(new TrendLineInfo(startMax.getDate(), previousMax.getDate(),"uptrend" ));
               if(isDownTrend) result.add(new TrendLineInfo(startMax.getDate(), previousMax.getDate(),"downtrend" ));
                break;
            }

            if(currentMax.getPrice() < previousMax.getPrice()){
                if(isDownTrend) {
                    previousMax = currentMax;
                }else if (isUpTrend){
                    result.add(new TrendLineInfo(startMax.getDate(), previousMax.getDate(), "uptrend"));
                    startMax = previousMax;
                    previousMax = currentMax;
                    isDownTrend = true;
                    isUpTrend = false;
                } else {
                    isDownTrend = true;
                    previousMax = currentMax;
                }
            }else{
                if(isUpTrend){
                    previousMax = currentMax;
                }else if (isDownTrend){
                    result.add(new TrendLineInfo(startMax.getDate(), previousMax.getDate(), "downtrend"));
                    startMax = previousMax;
                    previousMax = currentMax;
                    isDownTrend = false;
                    isUpTrend = true;
                } else {
                    isUpTrend = true;
                    previousMax = currentMax;
                }
            }
            if(!currentMax.getDate().equals(startMax.getDate())) {
                if (i == priceData.size() - windowSize & isUpTrend) {
                    result.add(new TrendLineInfo(startMax.getDate(), previousMax.getDate(), "uptrend"));
                } else if (i == priceData.size() - windowSize & isDownTrend) {
                    result.add(new TrendLineInfo(startMax.getDate(), previousMax.getDate(), "downtrend"));
                }
            }
        }
        return result;
    }

    private int getWindowSize(int range){
        if(range == 7) return 1;
        else if(range == 30) return 3;
        else if(range == 365) return 9;
        else if(range == 365 * 2) return 18;
        else if(range == 365 * 5) return 45;
        else if(range == 365 * 10) return 92;
        else throw new IllegalArgumentException("Invalid range " + range);
    }
}
