package ru.shishkin.docker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final StockManager stockManager;

    public Controller() {
        stockManager = new StockManager();
        reset();
    }

    @RequestMapping("/reset")
    public void reset() {
        stockManager.clear();

        stockManager.ipo("yndx", 100, 1500);
        stockManager.ipo("mgnt", 200, 4500);
        stockManager.ipo("aapl", 400, 120);
        stockManager.ipo("rblx", 10, 9500);
    }

    @RequestMapping("/ipo")
    public boolean ipo(@RequestParam final String name, @RequestParam final int count, @RequestParam final int price) {
        return stockManager.ipo(name, count, price);
    }

    @RequestMapping("/buy")
    public int buy(@RequestParam final String name, @RequestParam final int count, @RequestParam final int price) {
        return stockManager.sell(name, count, price);
    }

    @RequestMapping("/getPrice")
    public int getPrice(@RequestParam final String name) {
        return stockManager.getPrice(name);
    }

    @RequestMapping("/sell")
    public int sell(@RequestParam final String name, @RequestParam final int count, @RequestParam final int price) {
        return stockManager.buy(name, count, price);
    }

    @RequestMapping("/setPrice")
    public void setPrice(@RequestParam final String name, @RequestParam final int price) {
        stockManager.setPrice(name, price);
    }
}