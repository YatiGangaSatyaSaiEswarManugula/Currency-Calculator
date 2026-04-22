const currencies = [
    {
        name: "US Dollar",
        shortName: "USD",
        usdBaseRate: 1.0
    },
    {
        name: "Euro",
        shortName: "EUR",
        usdBaseRate: 0.93
    },
    {
        name: "British Pound",
        shortName: "GBP",
        usdBaseRate: 0.66
    },
    {
        name: "Swiss Franc",
        shortName: "CHF",
        usdBaseRate: 1.01
    },
    {
        name: "Chinese Yuan",
        shortName: "CNY",
        usdBaseRate: 6.36
    },
    {
        name: "Japanese Yen",
        shortName: "JPY",
        usdBaseRate: 123.54
    },
    {
        name: "Indian Rupee",
        shortName: "INR",
        usdBaseRate: 83.1
    },
    {
        name: "Australian Dollar",
        shortName: "AUD",
        usdBaseRate: 1.53
    },
    {
        name: "Canadian Dollar",
        shortName: "CAD",
        usdBaseRate: 1.36
    },
    {
        name: "UAE Dirham",
        shortName: "AED",
        usdBaseRate: 3.67
    }
];

const byCode = Object.fromEntries(
    currencies.map((currency) => [
        currency.shortName,
        {
            ...currency,
            exchangeValues: Object.fromEntries(
                currencies.map((targetCurrency) => [
                    targetCurrency.shortName,
                    Math.round((targetCurrency.usdBaseRate / currency.usdBaseRate) * 10000) / 10000
                ])
            )
        }
    ])
);

const amountInput = document.getElementById("amountInput");
const fromCurrency = document.getElementById("fromCurrency");
const toCurrency = document.getElementById("toCurrency");
const resultValue = document.getElementById("resultValue");
const resultMeta = document.getElementById("resultMeta");
const errorMessage = document.getElementById("errorMessage");
const converterForm = document.getElementById("converterForm");
const swapButton = document.getElementById("swapButton");

function populateSelect(select, selectedCode) {
    currencies.forEach((currency) => {
        const option = document.createElement("option");
        option.value = currency.shortName;
        option.textContent = `${currency.shortName} - ${currency.name}`;
        option.selected = currency.shortName === selectedCode;
        select.appendChild(option);
    });
}

function convertAmount(amount, fromCode, toCode) {
    const source = byCode[fromCode];
    if (!source) {
        throw new Error("Unknown source currency.");
    }

    const rate = source.exchangeValues[toCode];
    if (typeof rate !== "number") {
        throw new Error("Conversion not available.");
    }

    return Math.round(amount * rate * 100) / 100;
}

function formatMoney(value, currencyCode) {
    return new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: currencyCode,
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    }).format(value);
}

function showResult(amount, fromCode, toCode) {
    const converted = convertAmount(amount, fromCode, toCode);
    resultValue.textContent = formatMoney(converted, toCode);
    resultMeta.textContent = `${formatMoney(amount, fromCode)} converts to ${formatMoney(converted, toCode)} at the project's fixed rate.`;
    errorMessage.textContent = "";
}

function handleSubmit(event) {
    event.preventDefault();

    const amount = Number.parseFloat(amountInput.value);
    if (!Number.isFinite(amount) || amount < 0) {
        errorMessage.textContent = "Please enter a valid amount greater than or equal to 0.";
        resultMeta.textContent = "Enter an amount and choose currencies.";
        resultValue.textContent = "0.00";
        return;
    }

    try {
        showResult(amount, fromCurrency.value, toCurrency.value);
    } catch (error) {
        errorMessage.textContent = error.message;
    }
}

function swapCurrencies() {
    const currentFrom = fromCurrency.value;
    fromCurrency.value = toCurrency.value;
    toCurrency.value = currentFrom;

    if (amountInput.value.trim() !== "") {
        converterForm.requestSubmit();
    }
}

populateSelect(fromCurrency, "USD");
populateSelect(toCurrency, "EUR");
amountInput.value = "100";
showResult(100, "USD", "EUR");

converterForm.addEventListener("submit", handleSubmit);
swapButton.addEventListener("click", swapCurrencies);
