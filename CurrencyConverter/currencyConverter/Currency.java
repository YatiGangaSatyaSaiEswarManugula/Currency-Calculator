package currencyConverter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Currency {

    private String name;
    private String shortName;
    private LinkedHashMap<String, Double> exchangeValues = new LinkedHashMap<>();

    private static final LinkedHashMap<String, String> CURRENCY_NAMES = new LinkedHashMap<>();
    private static final LinkedHashMap<String, Double> USD_BASE_RATES = new LinkedHashMap<>();

    static {
        CURRENCY_NAMES.put("USD", "US Dollar");
        CURRENCY_NAMES.put("EUR", "Euro");
        CURRENCY_NAMES.put("GBP", "British Pound");
        CURRENCY_NAMES.put("CHF", "Swiss Franc");
        CURRENCY_NAMES.put("CNY", "Chinese Yuan");
        CURRENCY_NAMES.put("JPY", "Japanese Yen");
        CURRENCY_NAMES.put("INR", "Indian Rupee");
        CURRENCY_NAMES.put("AUD", "Australian Dollar");
        CURRENCY_NAMES.put("CAD", "Canadian Dollar");
        CURRENCY_NAMES.put("AED", "UAE Dirham");

        USD_BASE_RATES.put("USD", 1.0000);
        USD_BASE_RATES.put("EUR", 0.9300);
        USD_BASE_RATES.put("GBP", 0.6600);
        USD_BASE_RATES.put("CHF", 1.0100);
        USD_BASE_RATES.put("CNY", 6.3600);
        USD_BASE_RATES.put("JPY", 123.5400);
        USD_BASE_RATES.put("INR", 83.1000);
        USD_BASE_RATES.put("AUD", 1.5300);
        USD_BASE_RATES.put("CAD", 1.3600);
        USD_BASE_RATES.put("AED", 3.6700);
    }

    public Currency(String nameValue, String shortNameValue) {
        this.name = nameValue;
        this.shortName = shortNameValue;
    }

    public String getName() {
        return this.name;
    }

    public String getShortName() {
        return this.shortName;
    }

    public LinkedHashMap<String, Double> getExchangeValues() {
        return this.exchangeValues;
    }

    public void setExchangeValues(String key, Double value) {
        this.exchangeValues.put(key, value);
    }

    public void defaultValues() {
        Double usdRate = USD_BASE_RATES.get(this.shortName);
        if (usdRate == null) {
            return;
        }

        for (Map.Entry<String, Double> entry : USD_BASE_RATES.entrySet()) {
            double convertedRate = entry.getValue() / usdRate;
            exchangeValues.put(entry.getKey(), roundRate(convertedRate));
        }
    }

    public static ArrayList<Currency> init() {
        ArrayList<Currency> currencies = new ArrayList<>();

        for (Map.Entry<String, String> entry : CURRENCY_NAMES.entrySet()) {
            currencies.add(new Currency(entry.getValue(), entry.getKey()));
        }

        for (Currency c : currencies) {
            c.defaultValues();
        }

        return currencies;
    }

    public static String[] getCurrencyCodes() {
        return CURRENCY_NAMES.keySet().toArray(new String[0]);
    }

    public static double convert(double amount, double rate) {
        double result = amount * rate;
        return Math.round(result * 100.0) / 100.0;
    }

    private static double roundRate(double rate) {
        return Math.round(rate * 10000.0) / 10000.0;
    }
}
