package algorithms.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Sorting_MergeSort {
    
    private static List<Integer> merge(List<Integer> a, List<Integer> b) {
        List<Integer> listOne = new ArrayList<>(a);
        List<Integer> listTwo = new ArrayList<>(b);

        List<Integer> result = new ArrayList<>();

        Integer currentMax;

        while (!listOne.isEmpty() || !listTwo.isEmpty()) {
            if (listOne.isEmpty()) {
                result.addAll(listTwo);
                break;
            }
            if (listTwo.isEmpty()) {
                result.addAll(listOne);
                break;
            }

            if (listOne.get(0) < listTwo.get(0)) {
                currentMax = listOne.remove(0);
            } else {
                currentMax = listTwo.remove(0);
            }
            result.add(currentMax);
        }
        return result;
    }

    public static List<Integer> mergeSort(List<Integer> list) {
        if (list.size() == 1) return list;
        List<Integer> firstHalf = list.stream().limit(list.size() / 2).collect(Collectors.toList());
        List<Integer> secondHalf = list.stream().skip(list.size() / 2).collect(Collectors.toList());
        return merge(mergeSort(firstHalf), mergeSort(secondHalf));
    }

    public static void main(String[] args) {

        System.out.println(mergeSort(new Random().ints(10, 0, 50).boxed().collect(Collectors.toList())));
    }
}
