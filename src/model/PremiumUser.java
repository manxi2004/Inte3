package model;

import java.util.ArrayList;

public class PremiumUser extends User {
    private ArrayList<Book> booksBought;
    private ArrayList<Magazine> magazinesSubscribed;

    // This is the implementation of a `PremiumUser` class that extends the `User`
    // class. It has two
    // instance variables `booksBought` and `magazinesSubscribed` which are
    // ArrayLists of `Book` and
    // `Magazine` objects respectively. The constructor initializes these ArrayLists
    // as empty.
    public PremiumUser(String name, String id, String joinDate) {
        super(name, id, joinDate);
        booksBought = new ArrayList<>();
        magazinesSubscribed = new ArrayList<>();
    }

    public ArrayList<Book> getBooksBought() {
        return booksBought;
    }

    public ArrayList<Magazine> getMagazinesSubscribed() {
        return magazinesSubscribed;
    }

    public void buyBook(Book book) {
        booksBought.add(book);
    }

    public void subscribeToMagazine(Magazine magazine) {
        magazinesSubscribed.add(magazine);
    }
}
