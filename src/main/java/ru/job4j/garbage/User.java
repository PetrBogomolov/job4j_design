package ru.job4j.garbage;

public class User {

    private int field1;
    private String field2;

    public User() {
    }

    public User(int field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s%n", field1, field2);
    }
}
