package ru.job4j.ood.lsp.storage_foods.interfaces;

import ru.job4j.ood.lsp.storage_foods.foods.Food;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Warehouse implements Storage {
    private Map<Food, Integer> storageWarehouse = new HashMap<>();

    @Override
    public void addStorage(Food food) {
        storageWarehouse.merge(food, 1, Integer::sum);
        System.out.println("in Warehouse");
    }

    @Override
    public boolean accept(Food food) {
        return getPercent(food) < 25;
    }

    @Override
    public Set<Map.Entry<Food, Integer>> showStorage() {
        return storageWarehouse.entrySet();
    }
}
