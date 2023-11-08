package ru.shishkin.docker;

import java.util.HashMap;
import java.util.Map;

public class StockManager {
    private final Map<String, Stock> stocks = new HashMap<>();

    public synchronized void clear() {
        stocks.clear();
    }

    public synchronized boolean ipo(final String name, final int count, final int price) {
        System.out.println("IPO " + name + " " + count + " " + price);
        return stocks.putIfAbsent(name, new Stock(name, count, price)) == null;
    }

    public synchronized int sell(final String name, final int count, final int price) {
        System.out.println("Sell try " + name + " " + count + " " + price);
        Stock stock = stocks.get(name);
        if (stock.getPrice() == price) {
            int willSold = Math.min(count, stock.getCount());
            stock.changeCount(-willSold);
            System.out.println(willSold + " sold");
            return willSold;
        } else {
            System.out.println("None sold");
            return 0;
        }
    }

    public synchronized int buy(final String name, final int count, final int price) {
        System.out.println("Sell try " + name + " " + count + " " + price);
        Stock stock = stocks.get(name);
        if (stock.getPrice() == price) {
            stock.changeCount(count);
            System.out.println(count + " bought");
            return count;
        } else {
            System.out.println("None bought");
            return 0;
        }
    }

    public synchronized void setPrice(final String name, final int price) {
        System.out.println("SetPrice " + name + " " + price);
        Stock stock = stocks.get(name);
        stock.setPrice(price);
    }

    public synchronized int getPrice(final String name) {
        System.out.println("GetPrice " + name);
        Stock stock = stocks.get(name);
        return stock.getPrice();
    }
}
