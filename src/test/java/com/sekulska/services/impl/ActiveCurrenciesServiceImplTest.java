package com.sekulska.services.impl;

import com.sekulska.datacheck.impl.ActiveCurrenciesChecker;
import com.sekulska.model.ActiveCurrencyInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ActiveCurrenciesServiceImplTest {
    @Mock
    private ActiveCurrenciesChecker activeCurrenciesChecker;

    @InjectMocks
    ActiveCurrenciesServiceImpl cut;

    @Before
    public void loadActiveCurrenciesInfos() throws IOException {
        String input = "{\"AFN\" : \"Afghan Afghani\",\n" +
                " \"WZX\" : \"WZ WZZ WZZX\",\n"
               + "    \"AED\" : \"United Arab Emirates Dirham\"}";

        Mockito.when(activeCurrenciesChecker.getActiveCurrencies()).thenReturn(input);
        cut.loadActiveCurrencies();
    }

    @Test
    public void testCheckIfMethodReturnedCorrectlyListOfActiveCurrencyInfo() throws IOException {

        List<ActiveCurrencyInfo> activeCurrencyInfos = cut.getActiveCurrencies();

        List<ActiveCurrencyInfo> expected = new ArrayList<>();
        expected.add(new ActiveCurrencyInfo("WZX", "WZ WZZ WZZX"));
        expected.add(new ActiveCurrencyInfo("AED", "United Arab Emirates Dirham"));
        expected.add(new ActiveCurrencyInfo("AFN", "Afghan Afghani"));

        assertTrue(activeCurrencyInfos.containsAll(expected));
        assertTrue(expected.containsAll(activeCurrencyInfos));
    }

    @Test
    public void testCheckIfReturnedListOfActiveCurrencyInfoHasCorrectlySize() throws IOException {
        assertEquals(3, cut.getActiveCurrencies().size());
    }

    @Test
    public void testCheckIfReturnedListIsSortedAlphabetically(){
        List<ActiveCurrencyInfo> activeCurrencyInfos = cut.getActiveCurrencies();

       assertEquals("WZX", cut.getActiveCurrencies().get(2).getShortName());
       assertEquals("WZ WZZ WZZX", cut.getActiveCurrencies().get(2).getFullName());

       assertEquals("AFN", cut.getActiveCurrencies().get(1).getShortName());
       assertEquals("Afghan Afghani", cut.getActiveCurrencies().get(1).getFullName());

       assertEquals("AED", cut.getActiveCurrencies().get(0).getShortName());
       assertEquals("United Arab Emirates Dirham", cut.getActiveCurrencies().get(0).getFullName());
    }



}