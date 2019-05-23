package com.sekulska.datacheck;

import java.io.IOException;

public interface CurrenciesChecker {
    String getActiveCurrencies() throws IOException;
}
