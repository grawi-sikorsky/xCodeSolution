package pl.jsikora.xcodesolution.services;

import org.junit.jupiter.api.Test;
import pl.jsikora.xcodesolution.dto.RequestCurrencyDTO;
import pl.jsikora.xcodesolution.dto.ResponseExchangeRateDTO;
import pl.jsikora.xcodesolution.exceptions.BadExchangeResponseException;

import static org.junit.jupiter.api.Assertions.*;

public class ExchangeRateServiceTest {

    private final ExchangeRateService exchangeRateService = new ExchangeRateService();
    private final static RequestCurrencyDTO requestedCurrency = new RequestCurrencyDTO();

    @Test
    void getExchange_should_return_good_currency(){
        ResponseExchangeRateDTO responseExchangeRateDTO = new ResponseExchangeRateDTO();
        requestedCurrency.setCurrency("USD");

        responseExchangeRateDTO = exchangeRateService.getExchangeRateFromAPI(requestedCurrency);

        assertNotNull(responseExchangeRateDTO.getValue());
    }

    @Test
    void getExchange_should_throw_exception_on_invalid_currency(){
        requestedCurrency.setCurrency("XXX");
        assertThrows(BadExchangeResponseException.class, () -> { exchangeRateService.getExchangeRateFromAPI(requestedCurrency); });
    }
}
