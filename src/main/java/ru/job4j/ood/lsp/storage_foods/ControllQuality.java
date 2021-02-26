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
        ControllQuality control = new ControllQuality(List.of(new Shop(), new Warehouse(), new Trash()));
        control.distribute(bananTrash);
        control.distribute(bananTrash);
        control.distribute(milkWarehouse);
        control.distribute(fishShop);
        control.distribute(fishDiscount);
        control.distribute(fishDiscount);
        control.showStorages();
    }
}
