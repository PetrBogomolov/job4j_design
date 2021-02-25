package ru.job4j.ood.lsp.storage_foods.interfaces;

import ru.job4j.ood.lsp.storage_foods.foods.Food;

import java.util.Map;
import java.util.Set;

public interface Storage {

    void addStorage(Food food);

    Set<Map.Entry<Food, Integer>> showStorage();
}
