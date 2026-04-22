package currencyConverter;

import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        ArrayList<Currency> currencies = Currency.init();
        Scanner sc = new Scanner(System.in);

        System.out.println("===== Currency Converter =====");

        while (true) {

            System.out.println("\nAvailable Currencies:");
            for (Currency c : currencies) {
                System.out.println(c.getShortName());
            }

            System.out.print("From: ");
            String from = sc.next().toUpperCase();

            System.out.print("To: ");
            String to = sc.next().toUpperCase();

            System.out.print("Amount: ");
            double amount = sc.nextDouble();

            Currency fromCurrency = null;

            for (Currency c : currencies) {
                if (c.getShortName().equals(from)) {
                    fromCurrency = c;
                    break;
                }
            }

            if (fromCurrency == null) {
                System.out.println("Invalid currency!");
                continue;
            }

            Double rate = fromCurrency.getExchangeValues().get(to);

            if (rate == null) {
                System.out.println("Conversion not available!");
                continue;
            }

            double result = Currency.convert(amount, rate);
            System.out.println("Result: " + result + " " + to);
        }
    }
}