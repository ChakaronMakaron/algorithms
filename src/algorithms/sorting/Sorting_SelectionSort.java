package algorithms.sorting;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Sorting_SelectionSort {
    
    public static void selectionSort(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int min = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(min)) < 0) {
                    min = j;
                }
            }
            swap(list, i, min);
        }
    }

    private static void swap(List<Integer> list, int a, int b) {
        Integer tmp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, tmp);
    }

    public static void main(String[] args) {
        List<Integer> list = new Random().ints(10, 1, 30).boxed().collect(Collectors.toList());
        System.out.println("Before sorting: " + list);
        selectionSort(list);
        System.out.println("After sorting: " + list);
    }
}
