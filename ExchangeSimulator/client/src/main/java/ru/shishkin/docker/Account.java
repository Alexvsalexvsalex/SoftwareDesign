package ru.shishkin.docker;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private final String name;
    private final Map<String, Integer> count;
    private int balance;

    public Account(final String name) {
        this.name = name;
        this.count = new HashMap<>();
        this.balance = 0;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getStocks() {
        return Map.copyOf(count);
    }

    public int getCount(final String name) {
        return count.getOrDefault(name, 0);
    }

    public void changeCount(final String name, final int count) {
        int currentCount = getCount(name);
        assert currentCount >= -count;
        int nextCount = count + currentCount;
        if (nextCount == 0) {
            this.count.remove(name);
        } else {
            this.count.put(name, nextCount);
        }
    }

    public int getBalance() {
        return balance;
    }

    public void changeBalance(final int balance) {
        assert this.balance >= -balance;
        this.balance += balance;
    }
}
