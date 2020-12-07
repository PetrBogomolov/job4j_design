package ru.job4j.it.mail;

import java.util.*;

/**
 * Класс для реализации задания Почта. [#184458]
 */
public class UniqueAccount {
    /**
     * accounts хранит связку данных: пользователь = множество email
     */
    private Map<User, Set<String>> accounts = new HashMap<>();

    /**
     * Метод добавления пользователя в мапу
     *
     * @param user Объект класса User - пользователь
     */
    public void addUser(User user) {
        accounts.putIfAbsent(user, new HashSet<String>());
    }

    /**
     * Метод привязывания email к пользователю
     *
     * @param name имя пользователя
     * @param string адрес эл. почты
     */
    public void addEmail(String name, String string) {
        User user = findByName(name).get();
        Set<String> email = accounts.get(user);
        accounts.merge(user,email, (a, b) -> added(b, string));
    }

    /**
     * Метод добавляет элемент в множество.
     * Используется для передачи в фукнцию в лямбда выражении при вызове метода
     * Map.merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction)
     * т.к метод Set.add() нельзя использовать на прямую, т.к он возвращает boolean;
     *
     * @param set множество куда необходимо произвести добавление
     * @param str элемент, который добавляем
     * @return множество с добавленным элементом
     */
    private Set<String> added(Set<String> set, String str) {
        set.add(str);
        return set;
    }

    /**
     * Метод ищет пользователя по его имени
     *
     * @param name имя пользователя
     * @return объект класса User в Optional обертке
     */
    public Optional<User> findByName(String name) {
        return accounts.keySet().stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();
    }

    /**
     * Метод находит множество email у пользователя
     *
     * @param user пользователь, чьи email находит метод
     * @return множество email
     */
    public Set<String> findEmail(User user) {
        return accounts.get(user);
    }

    /**
     * Метод находит общие email у пользователей.
     * Если он нашел общие email у двух пользователей,
     * он считает этих пользователей идентичными и объединяет их,
     * путем слияния множеств email второго и первого пользователя,
     * ключом остается первый пользователь.
     *
     * Set перевел в List для более быстрого доступа к ключам через индекс.
     *
     */
    public void mergeAccount() {
        Set<User> key = accounts.keySet();
        List<User> keys = new ArrayList<>(key);
        for (int i = 0; i < keys.size(); i++) {
            Set<String> userValue = accounts.get(keys.get(i));
            Set<String> nextUserValue = accounts.get(keys.get(i + 1));
            for (String str : nextUserValue) {
                if (userValue.contains(str)) {
                    userValue.addAll(nextUserValue);
                    nextUserValue.removeAll(nextUserValue);
                    return;
                }
            }
        }
    }
}
