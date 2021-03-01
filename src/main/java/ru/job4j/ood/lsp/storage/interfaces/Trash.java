package ru.job4j.ood.lsp.storage.interfaces;

import ru.job4j.ood.lsp.storage.foods.Food;
import java.util.*;

public class Trash implements Storage {
    private Map<Food, Integer> storageTrash = new HashMap<>();

    @Override
    public void addStorage(Food food) {
        storageTrash.merge(food, 1, Integer::sum);
    }

    @Override
    public boolean accept(Food food) {
        return getPercent(food) > 100;
    }

    @Override
    public Set<Map.Entry<Food, Integer>> showStorage() {
        return storageTrash.entrySet();
    }

    @Override
    public List<Food> getProducts() {
        List<Food> foods = new ArrayList<>();
        for (Food food : storageTrash.keySet()) {
            int value = storageTrash.get(food);
            while (value != 0) {
                foods.add(food);
                value--;
            }
        }
        return foods;
    }

    @Override
    public void clear() {
        storageTrash.clear();
    }
}
