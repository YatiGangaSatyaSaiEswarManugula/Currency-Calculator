package currencyConverter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CurrencyGUI {

    public static void main(String[] args) {

        ArrayList<Currency> currencies = Currency.init();

        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(400, 250);
        frame.setLayout(new FlowLayout());

        JTextField amountField = new JTextField(10);

        String[] codes = Currency.getCurrencyCodes();

        JComboBox<String> fromBox = new JComboBox<>(codes);
        JComboBox<String> toBox = new JComboBox<>(codes);

        JButton button = new JButton("Convert");
        JLabel result = new JLabel("Result: ");

        button.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String from = (String) fromBox.getSelectedItem();
                String to = (String) toBox.getSelectedItem();

                Currency fromCurrency = null;

                for (Currency c : currencies) {
                    if (c.getShortName().equals(from)) {
                        fromCurrency = c;
                        break;
                    }
                }

                double rate = fromCurrency.getExchangeValues().get(to);
                double res = Currency.convert(amount, rate);

                result.setText("Result: " + res + " " + to);

            } catch (Exception ex) {
                result.setText("Invalid Input!");
            }
        });

        frame.add(new JLabel("Amount:"));
        frame.add(amountField);
        frame.add(fromBox);
        frame.add(toBox);
        frame.add(button);
        frame.add(result);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
