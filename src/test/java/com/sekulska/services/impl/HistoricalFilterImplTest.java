package com.sekulska.services.impl;

import com.sekulska.datacheck.PriceData;
import com.sekulska.datacheck.ResourcesNotFoundException;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;


public class HistoricalFilterImplTest {

   private HistoricalFilterImpl cut = new HistoricalFilterImpl();

   private String path = "/home/ania/aplikacje/IdeaProjects2/CurrencyExchange/src/test/resources/test_eleven_price_data";

   private List<PriceData> getTestPriceData(String fileName){

        List<PriceData> testPriceData = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            String[] splitted = stream.collect(Collectors.joining()).split("[:,\"]");
            List<String> filtered = Arrays
                    .stream(splitted)
                    .filter(line -> !line.contains("date")&!line.contains("price")&!line.isEmpty()&!line.contains("{")&!line.contains("}"))
                    .collect(Collectors.toList());
             for(int i = 0 ; i < filtered.size() ; i++){
                 testPriceData.add(new PriceData(filtered.get(i), filtered.get(i+1)));
                 i++;
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testPriceData;
    }

   @Test
   public void testCheckIfDataAreCorrectlyFilteredForRange3AndStep1(){
      List<PriceData> priceData = getTestPriceData(path);
      List<PriceData> filtered = cut.filter(priceData, 3, 1);
      assertEquals(new PriceData("2019-05-21","1.1170" ), filtered.get(0));
      assertEquals(new PriceData("2019-05-20","1.1163" ), filtered.get(1));
      assertEquals(new PriceData("2019-05-19","1.1161" ), filtered.get(2));
   }

   @Test
   public void testCheckIfDataAreCorrectlyFilteredForRange10AndStep5(){
      List<PriceData> priceData = getTestPriceData(path);
      List<PriceData> filtered = cut.filter(priceData, 10, 5);

      assertEquals(new PriceData("2019-05-21","1.1170" ), filtered.get(0));
      assertEquals(new PriceData("2019-05-15","1.1207" ), filtered.get(1));
   }

   @Test
   public void testCheckIfDataAreCorrectlyFilteredForRange10AndStep2(){
      List<PriceData> priceData = getTestPriceData(path);
      List<PriceData> filtered = cut.filter(priceData, 10, 2);

      assertEquals(new PriceData("2019-05-21","1.1170" ), filtered.get(0));
      assertEquals(new PriceData("2019-05-19","1.1161" ), filtered.get(1));
      assertEquals(new PriceData("2019-05-16","1.1206" ), filtered.get(2));
      assertEquals(new PriceData("2019-05-14","1.1231" ), filtered.get(3));
      assertEquals(new PriceData("2019-05-12","1.1234" ), filtered.get(4));
   }
   @Test
   public void testCheckIfDataAreCorrectlyFilteredForRange3AndStep3(){
      List<PriceData> priceData = getTestPriceData(path);
      List<PriceData> filtered = cut.filter(priceData, 3, 3);

      assertEquals(new PriceData("2019-05-21","1.1170" ), filtered.get(0));
   }
   @Test
   public void testCheckIfDataAreCorrectlyFilteredForRange9AndStep4(){
      List<PriceData> priceData = getTestPriceData(path);
      List<PriceData> filtered = cut.filter(priceData, 9, 4);

      assertEquals(new PriceData("2019-05-21","1.1170" ), filtered.get(0));
      assertEquals(new PriceData("2019-05-16","1.1206" ), filtered.get(1));
      assertEquals(new PriceData("2019-05-12","1.1234" ), filtered.get(2));
   }

    @Test(expected = ResourcesNotFoundException.class)
    public void testCheckIfErrorMessageIsHandledWhenThereIsNotEnoughDataForSuchRange() throws JSONException {
       cut.filter(getTestPriceData(path), 100, 9);
    }

    @Test
    public void testCheckIfFilteredListHasCorrectlySize(){
       List<PriceData> priceData = getTestPriceData(path);
       assertEquals(3, cut.filter(priceData, 3, 1).size());
       assertEquals(2, cut.filter(priceData, 10, 5).size());
       assertEquals(5, cut.filter(priceData, 10, 2).size());
       assertEquals(1, cut.filter(priceData, 3, 3).size());
       assertEquals(3, cut.filter(priceData, 9, 4).size());
    }


}