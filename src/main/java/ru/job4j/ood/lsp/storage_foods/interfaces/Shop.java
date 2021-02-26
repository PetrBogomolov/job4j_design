package ru.job4j.ood.lsp.storage_foods.interfaces;

import ru.job4j.ood.lsp.storage_foods.foods.Food;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Shop implements Storage {
    private final static int DISCOUNT = 50;
    private Map<Food, Integer> storageShop = new HashMap<>();

    @Override
    public void addStorage(Food food) {
        storageShop.merge(food, 1, Integer::sum);
        System.out.println("in shop");
    }

    @Override
    public boolean accept(Food food) {
        double percent = getPercent(food);
        if (percent >= 75 && percent <= 100) {
            food.setDiscount(setDiscount());
        }
        return percent >= 25 && percent <= 100;
    }

    @Override
    public Set<Map.Entry<Food, Integer>> showStorage() {
        return storageShop.entrySet();
    }

    private int setDiscount() {
        return DISCOUNT;
    }
}
