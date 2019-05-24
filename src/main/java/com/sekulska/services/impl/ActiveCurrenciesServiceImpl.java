package com.sekulska.services.impl;

import com.sekulska.datacheck.impl.ActiveCurrenciesChecker;
import com.sekulska.model.ActiveCurrencyInfo;
import com.sekulska.services.ActiveCurrenciesService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Service
@Scope("singleton")
public class ActiveCurrenciesServiceImpl implements ActiveCurrenciesService {

    @Autowired
    private ActiveCurrenciesChecker activeCurrenciesChecker;

    private List<ActiveCurrencyInfo> activeCurrencies = new ArrayList<>();

    @PostConstruct
    public void loadActiveCurrencies() throws IOException {
        JSONObject jsonObject = new JSONObject(activeCurrenciesChecker.getActiveCurrencies());
        Iterator iterator = jsonObject.keys();

        while (iterator.hasNext()){
            String key = (String) iterator.next();
            String value = jsonObject.getString(key);
            activeCurrencies.add(new ActiveCurrencyInfo(key, value));
        }
    }

    @Override
    public List<ActiveCurrencyInfo> getActiveCurrencies() {
        activeCurrencies.sort(Comparator.comparing(ActiveCurrencyInfo::getShortName));
        return activeCurrencies;
    }
}
