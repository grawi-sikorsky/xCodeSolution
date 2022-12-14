package pl.jsikora.xcodesolution.models;

import java.util.List;

public class NBPCurrencyModel {

    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;

    public static class Rate {
        public String no;
        public String effectiveDate;
        private double mid;

        public double getMid() {
            return mid;
        }
    }

    public List<Rate> getRates() {
        return rates;
    }
}
