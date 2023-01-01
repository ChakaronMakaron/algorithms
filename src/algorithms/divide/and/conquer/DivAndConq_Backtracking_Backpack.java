package algorithms.divide.and.conquer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import helper.classes.Item;

public class DivAndConq_Backtracking_Backpack {
    
    static int backpack(List<Item> items, int capacity) {
        List<Integer> prices = items.stream().map(e -> e.getPrice()).collect(Collectors.toList());
        List<Integer> weights = items.stream().map(e -> e.getWeight()).collect(Collectors.toList());
        return doBackpack(items.size() - 1, capacity, prices, weights);
    }

    static int doBackpack(int idx, int capacity, List<Integer> prices, List<Integer> weights) {
        if (capacity == 0 || idx < 0) return 0;

        if (weights.get(idx) > capacity) {
            return doBackpack(idx - 1, capacity, prices, weights);
        }

        return maxOf(doBackpack(idx - 1, capacity, prices, weights),
                        doBackpack(idx - 1, capacity - weights.get(idx), prices, weights) + prices.get(idx)); 
    }

    static int maxOf(int... args) {
        return Arrays.stream(args).max().getAsInt();
    }

    public static void main(String[] args) {
        // w, p
        List<Item> items = List.of(new Item(42, 43), new Item(13, 64), new Item(56, 32), new Item(71, 64), new Item(15, 67));

        System.out.println(backpack(items, 120));
    }
}
