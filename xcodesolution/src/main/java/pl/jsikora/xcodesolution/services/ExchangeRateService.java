package pl.jsikora.xcodesolution.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.jsikora.xcodesolution.dto.RequestCurrencyDTO;
import pl.jsikora.xcodesolution.dto.ResponseExchangeRateDTO;
import pl.jsikora.xcodesolution.exceptions.BadExchangeResponseException;
import pl.jsikora.xcodesolution.models.NBPCurrencyModel;

@Service
public class ExchangeRateService {
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseExchangeRateDTO getExchangeRateFromAPI(RequestCurrencyDTO requestedCurrency) {
        try {
            NBPCurrencyModel exRate = restTemplate.getForObject(
                    "https://api.nbp.pl/api/exchangerates/rates/a/{currency}?format=json",
                    NBPCurrencyModel.class,
                    Map.of("currency", requestedCurrency.getCurrency()));
            return new ResponseExchangeRateDTO(Optional.ofNullable(exRate).orElseThrow().getRates().get(0).getMid());
        } catch (Exception ex) {
            throw new BadExchangeResponseException("No such currency, or bad request to NBP currency API.");
        }
    }
}
