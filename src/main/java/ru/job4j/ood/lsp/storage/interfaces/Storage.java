package ru.job4j.ood.lsp.storage.interfaces;

import ru.job4j.ood.lsp.storage.foods.Food;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Storage {

    /*
     * метод добаляет продукт в хранилище
     */
    void addStorage(Food food);

    /*
     * метод определяет удовлетворяет ли продукт условиям, чтобы попасть в хранилище
     */
    boolean accept(Food food);

    /*
     * метод возвращает содержимое хранилища
     */
    Set<Map.Entry<Food, Integer>> showStorage();

    /*
     * метод вычисляет на сколько процентов израсходован срок годности продукта
     */
    default double getPercent(Food food) {
        LocalDate today = LocalDate.now();
        double totalStorageLife =
                food.getExpiryDate().getDayOfYear() - food.getCreateDate().getDayOfYear();
        double daysFromDateCreated = today.getDayOfYear() - food.getCreateDate().getDayOfYear();
        return daysFromDateCreated / (totalStorageLife / 100);
    }

    /*
     * метод извлекает все содержимое из хранилища
     */
    List<Food> getProducts();

    /*
     * метод очищает содержимое хранилища
     */
    void clear();
}
