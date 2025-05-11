package br.edu.atitus.currencyservice.controllers;

import br.edu.atitus.currencyservice.entities.CurrencyEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.atitus.currencyservice.repositories.CurrencyRepository;


@RestController
@RequestMapping("currency")
public class CurrencyController {
    private final CurrencyRepository currencyRepository;

    public CurrencyController(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository; //INVERSÃO DE DEPENDENCIA (DIP)
    }

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/{value}/{source}/{target}")
    public ResponseEntity<CurrencyEntity> getCurrency(@PathVariable double value, @PathVariable String source, @PathVariable String target) throws Exception {
        CurrencyEntity currency = currencyRepository.findBySourceAndTargetCurrency(source, target)
                .orElseThrow(() -> new Exception("Currency not found"));

        currency.setConvertedValue(value * currency.getConvertedValue());
        currency.setEnvironment("Currency Service running at " + serverPort);

        return ResponseEntity.ok(currency);
    }

}
