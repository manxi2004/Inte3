package model;

import java.util.ArrayList;
import java.util.List;

public class MoneyCategory {
    private Category category;
    private double money;
    List<MoneyCategory> magazineSalesByCategory = new ArrayList<>();

    // This is a class called `MoneyCategory` that has a constructor and several
    // methods.
    public MoneyCategory(Category category, double money) {
        this.category = category;
        this.money = money;
    }

    public Category getCategory() {
        return category;
    }

    public double getMoney() {
        return money;
    }

    public void addMoney(double money) {
        this.money += money;
    }
}
