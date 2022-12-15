package structures;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> {
    
    private List<T> body;

    public MinHeap() {
        body = new ArrayList<>();
    }

    public int getParentIndex(int n) {
        if (n == 0) return -1;
        return (n - 1) / 2;
    }

    public T getChild(int n, Branch branch) {
        if (branch.equals(Branch.LEFT)) return body.get(2 * n + 1);
        else if (branch.equals(Branch.RIGHT)) return body.get(2 * n + 2);
        else return null;
    }

    public void add(T item) {
        body.add(item);
        bubbleUp(body.size() - 1);
    }

    private void bubbleUp(int n) {
        if (getParentIndex(n) == -1) return;
        if (body.get(n).compareTo(body.get(getParentIndex(n))) < 0) {
            swap(n, getParentIndex(n));
            bubbleUp(getParentIndex(n));
        }
    }

    private void swap(int a, int b) {
        T tmp = body.get(a);
        body.set(a, body.get(b));
        body.set(b, tmp);
    }

    @Override
    public String toString() {
        return body.toString();
    }

    public static enum Branch {
        LEFT, RIGHT
    }

    public static void main(String[] args) {
        MinHeap<Integer> minHeap = new MinHeap<>();
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

        System.out.println(minHeap);
        System.out.println(minHeap.getParentIndex(7));
    }
}
