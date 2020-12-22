package ru.job4j.it.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<V> {
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Node<K, V>[] table = new Node[16];
    private int elements = 0;
    private int modCount = 0;

    public boolean insert(K key, V value) {
        boolean result = false;
        Node<K, V> add = new Node<>(key, value);
        int index = findIndex(key, table.length);
        if (table[index] == null) {
            table[index] = add;
            elements++;
            modCount++;
            if (elements >= table.length * DEFAULT_LOAD_FACTOR) {
                resize();
            }
            result = true;
        }
        return result;
    }

    private int hash(K key) {
        int h = key.hashCode();
        return h ^ (h >>> 16);
    }

    private int findIndex(K key, int lenght) {
        return hash(key) % lenght;
    }

    private void resize() {
        Node<K, V>[] newTable = new Node[table.length * 2];
        for (Node<K, V> element : table) {
            if (element != null) {
                int newIndex = findIndex(element.key, newTable.length);
                newTable[newIndex] = element;
            }
        }
        table = newTable;
    }

    public V get(K key) {
        int index = findIndex(key, table.length);
        Node<K, V> result = table[index];
        return table[index] != null && key.equals(result.key) ? result.value : null;
    }

    public boolean delete(K key) {
        boolean result = false;
        int index = findIndex(key, table.length);
        if (table[index] != null && key.equals(table[index].key)) {
            table[index] = null;
            elements--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int index = -1;
            private int nextIndex = -1;
            private final int expModCount = modCount;

            @Override
            public boolean hasNext() {
                return index < nextIndex || index != findNext();
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                index = nextIndex;
                return table[index].value;
            }

            private int findNext() {
                for (int i = index + 1; i < table.length; i++) {
                    if (table[i] != null) {
                        nextIndex = i;
                        break;
                    }
                }
                return nextIndex;
            }
        };
    }

    private static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
