package pl.jsikora.xcodesolution.models;

import java.util.ArrayList;

public class NBPCurrencyModel {

    public String table;
    public String currency;
    public String code;
    public ArrayList<Rate> rates;

    public static class Rate{
        public String no;
        public String effectiveDate;
        public double mid;
    }
}
