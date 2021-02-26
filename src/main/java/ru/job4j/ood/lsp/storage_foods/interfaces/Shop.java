package ru.job4j.ood.lsp.storage_foods.interfaces;

import ru.job4j.ood.lsp.storage_foods.foods.Food;
import java.util.*;

public class Shop implements Storage {
    private final static int DISCOUNT = 50;
    private Map<Food, Integer> storageShop = new HashMap<>();

    @Override
    public void addStorage(Food food) {
        storageShop.merge(food, 1, Integer::sum);
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

    @Override
    public List<Food> getProducts() {
        List<Food> foods = new ArrayList<>();
        for (Food food : storageShop.keySet()) {
            int value = storageShop.get(food);
            while (value != 0) {
                foods.add(food);
                value--;
            }
        }
        return foods;
    }

    @Override
    public void clear() {
        storageShop.clear();
    }

    private int setDiscount() {
        return DISCOUNT;
    }
}
