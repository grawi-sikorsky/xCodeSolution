package pl.jsikora.xcodesolution.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jsikora.xcodesolution.dto.RequestCurrencyDTO;
import pl.jsikora.xcodesolution.dto.RequestNumbersDTO;
import pl.jsikora.xcodesolution.dto.ResponseExchangeRateDTO;
import pl.jsikora.xcodesolution.dto.ResponseNumbersDTO;
import pl.jsikora.xcodesolution.services.ExchangeRateService;
import pl.jsikora.xcodesolution.services.SortingService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"*", "http://localhost:4200"})
public class XcodeController {

    private final SortingService sortingService;
    private final ExchangeRateService exchangeRateService;

    public XcodeController(SortingService sortingService, ExchangeRateService exchangeRateService) {
        this.sortingService = sortingService;
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping(value = "status/ping")
    public ResponseEntity<String> pingpong() {
        return ResponseEntity.status(HttpStatus.OK).body("pong");
    }

    @PostMapping(value = "numbers/sort-command")
    public ResponseEntity<ResponseNumbersDTO> numbers(@RequestBody RequestNumbersDTO requestNumbers) {
        return ResponseEntity.status(HttpStatus.OK).body(sortingService.sortNumbers(requestNumbers));
    }

    @PostMapping(value = "currencies/get-current-currency-value-command")
    public ResponseEntity<ResponseExchangeRateDTO> currency(@RequestBody RequestCurrencyDTO requestedCurrency) {
        return ResponseEntity.status(HttpStatus.OK).body(exchangeRateService.getExchangeRateFromAPI(requestedCurrency));
    }
}
