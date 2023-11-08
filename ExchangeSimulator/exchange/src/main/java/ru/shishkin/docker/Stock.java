package ru.shishkin.docker;

public class Stock {
    private final String name;
    private int count;
    private int price;

    public Stock(final String name, final int count, final int price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void changeCount(final int count) {
        assert this.count >= -count;
        this.count += count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }
}
