package algorithms.sorting;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sorting_QuickSort {
    
    public static List<Integer> quicksort(List<Integer> list) {
        if (list.size() < 2) return list;
        Integer pivot = list.get(0);
        List<Integer> high = list.stream().skip(1).filter(el -> el.compareTo(pivot) >= 0).collect(Collectors.toList());
        List<Integer> low = list.stream().skip(1).filter(el -> el.compareTo(pivot) < 0).collect(Collectors.toList());
        return Stream.of(quicksort(low).stream(), List.of(pivot).stream(), quicksort(high).stream())
            .flatMap(Function.identity())
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        
        List<Integer> list = new Random().ints(10, 1, 20).boxed().collect(Collectors.toList());
        System.out.println(quicksort(list));
    }
}
