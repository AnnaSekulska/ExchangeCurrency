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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Scope("singleton")
public class ActiveCurrenciesServiceImpl implements ActiveCurrenciesService {

    @Autowired
    private ActiveCurrenciesChecker activeCurrenciesChecker;

    @Override
    @PostConstruct
    public List<ActiveCurrencyInfo> getActiveCurrencies() throws IOException {
        List<ActiveCurrencyInfo> activeCurrencyInfos = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(activeCurrenciesChecker.getActiveCurrencies());
        Iterator iterator = jsonObject.keys();

        while (iterator.hasNext()){
            String key = (String) iterator.next();
            String value = jsonObject.getString(key);
            activeCurrencyInfos.add(new ActiveCurrencyInfo(key, value));
        }
        return activeCurrencyInfos;
    }

}
