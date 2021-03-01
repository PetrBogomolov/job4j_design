package ru.job4j.ood.lsp.storage.interfaces;

import ru.job4j.ood.lsp.storage.foods.Food;
import java.util.*;

public class Warehouse implements Storage {
    private Map<Food, Integer> storageWarehouse = new HashMap<>();

    @Override
    public void addStorage(Food food) {
        storageWarehouse.merge(food, 1, Integer::sum);
    }

    @Override
    public boolean accept(Food food) {
        return getPercent(food) < 25;
    }

    @Override
    public Set<Map.Entry<Food, Integer>> showStorage() {
        return storageWarehouse.entrySet();
    }

    @Override
    public List<Food> getProducts() {
        List<Food> foods = new ArrayList<>();
        for (Food food : storageWarehouse.keySet()) {
            int value = storageWarehouse.get(food);
            while (value != 0) {
                foods.add(food);
                value--;
            }
        }
        return foods;
    }

    @Override
    public void clear() {
        storageWarehouse.clear();
    }
}
