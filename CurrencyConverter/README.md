# Currency Converter

A simple currency converter project with:

- a Java console app
- a Java Swing desktop UI
- a browser-based web interface

## Project Structure

- `currencyConverter/MainApp.java` - console version
- `currencyConverter/CurrencyGUI.java` - Swing desktop version
- `currencyConverter/index.html` - web interface
- `currencyConverter/script.js` - browser conversion logic
- `currencyConverter/styles.css` - web styling

## Supported Currencies

- USD - US Dollar
- EUR - Euro
- GBP - British Pound
- CHF - Swiss Franc
- CNY - Chinese Yuan
- JPY - Japanese Yen
- INR - Indian Rupee
- AUD - Australian Dollar
- CAD - Canadian Dollar
- AED - UAE Dirham

## Run The Web Version

Open `currencyConverter/index.html` in any web browser.

## Run The Java Version

Compile:

```powershell
javac currencyConverter\*.java
```

Run Swing UI:

```powershell
java currencyConverter.CurrencyGUI
```

Run console app:

```powershell
java currencyConverter.MainApp
```

## Notes

This project currently uses fixed exchange-rate values stored in the code.
