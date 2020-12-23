package ru.job4j.io.io.mail;

import java.util.*;

/**
 * Класс для реализации задания Почта. [#184458]
 */
public class UniqueAccount {
    /**
     * Метод находит общие email у пользователей.
     * Если он нашел общие email у двух пользователей,
     * он считает этих пользователей идентичными и объединяет их,
     * путем слияния множеств email-ов второго и первого пользователя,
     * ключом остается первый пользователь.
     *
     * @return новую мапу, в которой пользователи с одинаковыми значения были объединены
     */
    public Map<User, Set<String>> mergeAccount(Map<User, Set<String>> accounts) {
        Map<User, Set<String>> result = new HashMap<>();
        Map<String, User> emailToUser = new HashMap<>();
        Set<Map.Entry<User, Set<String>>> setAccounts = accounts.entrySet();
        User user = null;
        for (Map.Entry<User, Set<String>> elementOfAccounts : setAccounts) {
            for (String email : elementOfAccounts.getValue()) {
                user = emailToUser.get(email);
                if (user != null) {
                    break;
                }
            }
            if (user == null) {
                result.put(elementOfAccounts.getKey(), new HashSet<>(elementOfAccounts.getValue()));
                for (String email : elementOfAccounts.getValue()) {
                    emailToUser.put(email, elementOfAccounts.getKey());
                }
            }
            if (user != null) {
                Set<String> userEmails = result.get(user);
                for (String email : elementOfAccounts.getValue()) {
                    emailToUser.putIfAbsent(email, user);
                    userEmails.add(email);
                }
                result.put(user, userEmails);
            }
        }
        return result;
    }
}
