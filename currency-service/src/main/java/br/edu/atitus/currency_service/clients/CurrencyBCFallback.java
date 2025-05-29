package br.edu.atitus.currency_service.clients;

import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CurrencyBCFallback implements CurrencyBCClient {

    @Override
    public CurrencyBCReponse getCurrencyBC(String moeda) {
        CurrencyBCReponse fallback = new CurrencyBCReponse();
        fallback.setValue(Collections.emptyList());
        return fallback;
    }

}
