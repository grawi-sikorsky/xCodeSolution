package pl.jsikora.xcodesolution.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.jsikora.xcodesolution.dto.RequestCurrencyDTO;
import pl.jsikora.xcodesolution.dto.ResponseExchangeRateDTO;
import pl.jsikora.xcodesolution.exceptions.BadExchangeResponseException;
import pl.jsikora.xcodesolution.models.NBPCurrencyModel;

@Service
public class ExchangeRateService {
    
    public ResponseExchangeRateDTO getExchangeRateFromAPI(RequestCurrencyDTO requestedCurrency){
        // "https://api.nbp.pl/api/exchangerates/rates/a/usd?format=json"

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.nbp.pl/api/exchangerates/rates/a/")
                        .append(requestedCurrency.getCurrency())
                        .append("?format=json");

        final String uri = stringBuilder.toString();

        RestTemplate restTemplate = new RestTemplate();
        String curJson = restTemplate.getForObject(uri, String.class);

        String temp = curJson.substring(curJson.lastIndexOf("mid") + 5, curJson.indexOf("}]}"));

        return new ResponseExchangeRateDTO(Double.valueOf(temp));
    }

    public ResponseExchangeRateDTO getExchangeRateFromAPI2(RequestCurrencyDTO requestedCurrency){
        StringBuilder stringBuilder = new StringBuilder();
        RestTemplate restTemplate = new RestTemplate();
        stringBuilder.append("https://api.nbp.pl/api/exchangerates/rates/a/")
                        .append(requestedCurrency.getCurrency())
                        .append("?format=json");

        final String uri = stringBuilder.toString();

        NBPCurrencyModel exRate = new NBPCurrencyModel();

        try{
            exRate = restTemplate.getForObject(uri, NBPCurrencyModel.class);
        }
        catch(RuntimeException ex){
            throw new BadExchangeResponseException("No such currency, or bad request to NBP currency API.");
        }

        return new ResponseExchangeRateDTO(exRate.rates.get(0).mid);
    }
}
