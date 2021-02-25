package ru.job4j.ood.lsp.storage_foods;

import ru.job4j.ood.lsp.storage_foods.foods.Bananas;
import ru.job4j.ood.lsp.storage_foods.foods.Fish;
import ru.job4j.ood.lsp.storage_foods.foods.Food;
import ru.job4j.ood.lsp.storage_foods.foods.Milk;
import ru.job4j.ood.lsp.storage_foods.interfaces.Shop;
import ru.job4j.ood.lsp.storage_foods.interfaces.Storage;
import ru.job4j.ood.lsp.storage_foods.interfaces.Trash;
import ru.job4j.ood.lsp.storage_foods.interfaces.Warehouse;

import java.time.LocalDate;

public class ControllQuality {

    private Storage storage;

    public void distribute(Food food, int discount) {
        double percent = getPercent(food.getCreateDate(), food.getExpiryDate(), LocalDate.now());
        if (percent > 100) {
            storage = new Trash();
            System.out.println("in trash");
        } else if (percent < 25) {
            storage = new Warehouse();
            System.out.println("in warehouse");
        } else if (percent >= 25 && percent < 75) {
            storage = new Shop();
            System.out.println("in shop");
        } else if (percent >= 75 && percent <= 100) {
            storage = new Shop();
            food.setDiscount(discount);
            System.out.printf("in shop plus discount %d%n", discount);
        }
        storage.addStorage(food);
        System.out.println(storage.showStorage());
    }

    public double getPercent(LocalDate createDate, LocalDate expiryDate, LocalDate today) {
        double totalStorageLife = expiryDate.getDayOfYear() - createDate.getDayOfYear();
        double daysFromDateCreated = today.getDayOfYear() - createDate.getDayOfYear();
        return daysFromDateCreated / (totalStorageLife / 100);
    }

    public static void main(String[] args) {
        Food bananTrash = new Bananas("Banan",
                LocalDate.of(2021, 2, 1),
                LocalDate.of(2021, 2, 24),
                50);
        Food milkWarehouse = new Milk("Milk",
                LocalDate.of(2021, 2, 20),
                LocalDate.of(2021, 6, 25),
                80);
        Food fishShop = new Fish("Fish",
                LocalDate.of(2021, 2, 1),
                LocalDate.of(2021, 3, 20),
                150);
        Food fishDiscount = new Fish("Fish",
                LocalDate.of(2021, 2, 1),
                LocalDate.of(2021, 2, 28),
                150);
        ControllQuality control = new ControllQuality();
        control.distribute(bananTrash, 10);
        control.distribute(bananTrash, 10);
        control.distribute(milkWarehouse, 10);
        control.distribute(fishShop, 10);
        control.distribute(fishDiscount, 10);
        control.distribute(fishDiscount, 10);
    }
}
