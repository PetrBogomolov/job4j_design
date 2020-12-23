package ru.job4j.collectionpro.statistic;

import java.util.*;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Analize.Info info = new Info();
        Map<Integer, User> prev = convert(previous);
        for (User element : current) {
            if (!prev.containsKey(element.id)) {
                info.added++;
            }
            User user = prev.get(element.id);
            if (user != null) {
                if (!element.name.equals(user.name)) {
                    info.changed++;
                }
            }
        }
        info.deleted = Math.abs(current.size() - previous.size() - info.added);
        return info;
    }

    private Map<Integer, User> convert(List<User> list) {
        Map<Integer, User> result = new HashMap<>();
        for (User element : list) {
            result.put(element.id, element);
        }
        return result;
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                   && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
       private int added = 0;
       private int changed = 0;
       private int deleted = 0;

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}
