package ru.job4j.collectionpro.storage;

public class RoleStore {
    public static void main(String[] args) {
        MemStore<Base> bases = new MemStore<>();
        bases.add(new Base(12, "Base1"));
        bases.add(new Base(22, "Base2"));
        bases.add(new Base(32, "Base3"));
        System.out.println(bases.fyndId(12));
        bases.delete(22);
        bases.replace(32, new Base(33, "New Base"));
        bases.forEach(System.out::println);

        MemStore<User> user = new MemStore<>();
        user.add(new User(13, "Element1"));
        user.add(new User(23, "Element2"));
        user.add(new User(33, "Element3"));
        System.out.println(user.fyndId(13));
        user.delete(23);
        user.replace(33, new User(33, "New Element"));
        user.forEach(System.out::println);

        Store<User> users = new UserStore();
        users.add(new User(13, "Element1"));
        users.add(new User(23, "Element2"));
        users.add(new User(33, "Element3"));
        System.out.println(users.fyndId(13));
        users.delete(23);
        users.replace(33, new User(33, "New Element"));
        users.forEach(System.out::println);
    }
}
