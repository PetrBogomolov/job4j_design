package ru.job4j.ood.lsp.storage_foods.interfaces;

import ru.job4j.ood.lsp.storage_foods.foods.Food;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Trash implements Storage {
    private Map<Food, Integer> storageTrash = new HashMap<>();

    @Override
    public void addStorage(Food food) {
        storageTrash.merge(food, 1, Integer::sum);
        System.out.println("in trash");
    }

    @Override
    public boolean accept(Food food) {
        if (getPercent(food) > 100) {
            return true;
        }
        return false;
    }

    @Override
    public Set<Map.Entry<Food, Integer>> showStorage() {
        return storageTrash.entrySet();
    }
}
