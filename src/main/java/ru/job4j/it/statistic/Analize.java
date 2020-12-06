package ru.job4j.it.statistic;

import java.util.*;

public class Analize {
    private final Analize.Info info = new Info();

    public Info diff(List<User> previous, List<User> current) {
        info.added = added(previous, current);
        info.deleted = deleted(previous, current);
        info.changed = changed(previous, current);
        return info;
    }

    private int added(List<User> previous, List<User> current) {
        int result = 0;
        List<User> added = new ArrayList<>();
        for (User element : current) {
            if (!previous.contains(element)) {
                added.add(element);
                result = added.size();
            }
        }
        return result;
    }

    private int deleted(List<User> previous, List<User> current) {
        int result = 0;
        for (User element : previous) {
            if (current.contains(element)) {
                result = Math.abs(current.size() - previous.size() - info.added);
                break;
            }
        }
        return result;
    }

    private int changed(List<User> previous, List<User> current) {
        int result = 0;
        Map<Integer, User> prev = convert(previous);
        for (User element : current) {
            User prevUser = prev.get(element.getId());
            if (prevUser != null) {
                if (!element.getName().equals(prevUser.getName())) {
                    result++;
                }
            }
        }
        return result;
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

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
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
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return "User{"
                    + "id=" + id
                    + ", name='" + name + '\''
                    + '}';
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
