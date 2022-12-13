package pl.jsikora.xcodesolution.dto;

public class ResponseExchangeRateDTO {
    Double value;

    public ResponseExchangeRateDTO(Double value) {
        this.value = value;
    }

    public ResponseExchangeRateDTO() {
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
    
}
