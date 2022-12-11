package structures;

import static java.lang.Math.abs;
import static java.util.Objects.*;

@SuppressWarnings("unchecked")
public class MyHashTable<K, V> {

    private Entry<K, V>[] table;

    public MyHashTable() {
        this.table = (Entry<K, V>[]) new Entry[16];
    }

    public void put(K key, V value) {
        int hash = key.hashCode();
        int bucket = abs(hash) % table.length;
        
        if (isNull(table[bucket])) {
            table[bucket] = new Entry<K, V>(key, value, hash, null);
            return;
        }

        Entry<K, V> head = table[bucket];
        
        while (true) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }

            head = head.next;

            if (isNull(head)) break;
        }

        head.next = new Entry<K, V>(key, value, hash, null);
    }

    public V get(K key) {
        int hash = key.hashCode();
        int bucket = abs(hash) % table.length;
        
        if (isNull(table[bucket])) {
            return null;
        }

        Entry<K, V> head = table[bucket];

        while (true) {
            if (head.hashCode.equals(hash)) {
                return head.value;
            }

            head = head.next;

            if (isNull(head)) break;
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (Entry<K, V> entry : table) {
            if (nonNull(entry)) {
                result.append(entry + ", ");
                while (nonNull(entry.next)) {
                    entry = entry.next;
                    result.append(entry + ", ");
                }
            }
        }
        return result.toString() + "}";
    }

    private static class Entry<K, V> {

        private K key;
        private V value;
        private Integer hashCode;
        private Entry<K, V> next;
        
        private Entry(K key, V value, Integer hashCode, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
            this.next = next;
        }

        private Entry() {}

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
