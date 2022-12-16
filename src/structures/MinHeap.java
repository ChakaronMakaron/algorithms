package structures;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> {
    
    private List<T> body;

    public MinHeap() {
        body = new ArrayList<>();
    }

    public boolean isEmpty() {
        return body.isEmpty();
    }

    public int getParentIndex(int n) {
        if (n == 0) return -1;
        return (n - 1) / 2;
    }

    public int getChildIndex(int n, Branch branch) {
        if (branch.equals(Branch.LEFT)) return 2 * n + 1;
        else if (branch.equals(Branch.RIGHT)) return 2 * n + 2;
        else return -1;
    }

    public T getChild(int n, Branch branch) {
        if (branch.equals(Branch.LEFT)) return body.get(2 * n + 1);
        else if (branch.equals(Branch.RIGHT)) return body.get(2 * n + 2);
        else return null;
    }

    public T extractMin() {
        T min = body.get(0);
        body.set(0, body.get(body.size() - 1));
        body.remove(body.size() - 1);
        bubbleDown(0);
        return min;
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

    private void bubbleDown(int n) {
        int descendantIndex = getChildIndex(n, Branch.LEFT);
        int minIndex = n;
        for (int i = 0; i < body.size(); i++) {
            if ((descendantIndex + i) < (body.size())) {
                if (body.get(minIndex).compareTo(body.get(descendantIndex + i)) > 0) {
                    minIndex = descendantIndex + i;
                }
            }
        }
        if (minIndex != n) {
            swap(n, minIndex);
            bubbleDown(minIndex);
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
}
