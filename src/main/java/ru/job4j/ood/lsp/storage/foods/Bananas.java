package ru.job4j.ood.lsp.storage.foods;

import java.time.LocalDate;

public class Bananas extends Food {

    public Bananas(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        super(name, createDate, expiryDate, price);
    }
}
