package algorithms.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Sorting_InsertionSort {

    public static List<Integer> insertionSort(List<Integer> listToSort) {
        List<Integer> list = new ArrayList<>(listToSort);
        for (int i = 1; i < list.size(); i++) {
            int j = i;
            while (j > 0 && list.get(j - 1) > list.get(j)) {
                swap(list, j, j - 1);
                j--;
            }
        }
        return list;
    }
    
    public static void swap(List<Integer> list, int i, int j) {
        Integer tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    public static void main(String[] args) {
        
        List<Integer> list = new Random().ints(10, 1, 30).boxed().collect(Collectors.toList());

        System.out.println(insertionSort(list));
    }
}
