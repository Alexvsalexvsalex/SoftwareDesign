package database.model;

public enum Currency {
    RUB(1),
    DOLLAR(118),
    EURO(140);

    private final double value;

    Currency(double value) {
        this.value = value;
    }

    public double convert(Currency currency, double price) {
        return price * this.value / currency.value;
    }

    public static Currency getDefaultCurrency() {
        return DOLLAR;
    }

    public static double convertFromDefaultCurrency(Currency currency, double price) {
        return getDefaultCurrency().convert(currency, price);
    }
}
