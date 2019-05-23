package com.sekulska.services.impl;

import com.sekulska.datacheck.impl.ActiveCurrenciesChecker;
import com.sekulska.model.ActiveCurrencyInfo;
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

    @Test
    public void testCheckIfMethodReturnedCorrectlyListOfActiveCurrencyInfo() throws IOException {
        String input = "{\"AED\" : \"United Arab Emirates Dirham\",\n" +
                "    \"AFN\" : \"Afghan Afghani\"}";

        Mockito.when(activeCurrenciesChecker.getActiveCurrencies()).thenReturn(input);
        List<ActiveCurrencyInfo> activeCurrencyInfos = cut.getActiveCurrencies();

        List<ActiveCurrencyInfo> expected = new ArrayList<>();
        expected.add(new ActiveCurrencyInfo("AED", "United Arab Emirates Dirham"));
        expected.add(new ActiveCurrencyInfo("AFN", "Afghan Afghani"));

        assertTrue(activeCurrencyInfos.containsAll(expected));
        assertTrue(expected.containsAll(activeCurrencyInfos));
    }

    @Test
    public void testCheckIfReturnedListOfActiveCurrencyInfoHasCorrectlySize() throws IOException {
        String input = "{\"AED\" : \"United Arab Emirates Dirham\",\n" +
                "    \"AFN\" : \"Afghan Afghani\"}";

        Mockito.when(activeCurrenciesChecker.getActiveCurrencies()).thenReturn(input);

        assertEquals(2, cut.getActiveCurrencies().size());

    }

}