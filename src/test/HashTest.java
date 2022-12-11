package test;

import org.junit.Test;

import structures.MyHashTable;

public class HashTest {
    
    @Test
    public void hashTest() {
        MyHashTable<String, Integer> hashTable = new MyHashTable<>();
        hashTable.put("a", 15);
        hashTable.put("b", 15);
        hashTable.put("c", 15);

        System.out.println(hashTable);

        hashTable.put("c", 25);

        System.out.println(hashTable);
        System.out.println(hashTable.get("c"));
        System.out.println(hashTable.get("a"));
    }
}
