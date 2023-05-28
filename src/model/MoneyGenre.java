package model;

import java.util.ArrayList;
import java.util.List;

public class MoneyGenre {
    private Genre genre;
    private double money;
    List<MoneyGenre> bookSalesByGenre = new ArrayList<>();

    // This is a class called `MoneyGenre` that represents the amount of money
    // earned by a particular
    // genre. It has a constructor that takes in a `Genre` object and a `double`
    // representing the amount
    // of money earned by that genre. It also has methods to get the genre and the
    // amount of money
    // earned, as well as a method to add more money to the existing amount. The
    // class also has a list
    // of `MoneyGenre` objects representing the book sales by genre.
    public MoneyGenre(Genre genre, double money) {
        this.genre = genre;
        this.money = money;
    }

    public Genre getGenre() {
        return genre;
    }

    public double getMoney() {
        return money;
    }

    public void addMoney(double money) {
        this.money += money;
    }
}
