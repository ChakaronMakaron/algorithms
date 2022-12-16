package test;

import org.junit.Before;
import org.junit.Test;

import structures.MinHeap;

public class MinHeapTest {

    private MinHeap<Integer> minHeap;

    @Before
    public void createHeap() {
        minHeap = new MinHeap<>();
        minHeap.add(0);
        minHeap.add(10);
        minHeap.add(20);
        minHeap.add(30);
        minHeap.add(40);
        minHeap.add(50);

        minHeap.add(2);
        minHeap.add(3);
        minHeap.add(4);
        minHeap.add(5);
    }
    
    @Test
    public void test() {
        System.out.println("Heap before: " + minHeap);
        while (!minHeap.isEmpty()) {
            System.out.println("Extracting: " + minHeap.extractMin());
            System.out.println("After extraction: " + minHeap);
        }
    }
}
