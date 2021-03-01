package ru.job4j.ood.lsp.storage;

import ru.job4j.ood.lsp.storage.foods.Bananas;
import ru.job4j.ood.lsp.storage.foods.Fish;
import ru.job4j.ood.lsp.storage.foods.Food;
import ru.job4j.ood.lsp.storage.foods.Milk;
import ru.job4j.ood.lsp.storage.interfaces.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControllQuality {
    private final List<Storage> storages;

    public ControllQuality(List<Storage> storages) {
        this.storages = storages;
    }

    public void distribute(Food food) {
        for (Storage storage : storages) {
            if (storage.accept(food)) {
                storage.addStorage(food);
            }
        }
    }

    public void showStorages() {
        for (Storage storage : storages) {
            System.out.println(storage.showStorage());
        }
    }

    public void restore() {
        List<Food> allFoods = new ArrayList<>();
        for (Storage storage : storages) {
            allFoods.addAll(storage.getProducts());
            storage.clear();
        }
        allFoods.forEach(this::distribute);
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
        ControllQuality control = new ControllQuality(
                List.of(new Shop(), new Warehouse(), new Trash())
        );
        control.distribute(bananTrash);
        control.distribute(bananTrash);
        control.distribute(milkWarehouse);
        control.distribute(fishShop);
        control.distribute(fishDiscount);
        control.distribute(fishDiscount);
        control.showStorages();
        control.restore();
        control.showStorages();
    }
}
