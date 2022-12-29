package algorithms.misc;

import java.util.ArrayList;
import java.util.List;

public class Backtracking_PermutationsGeneration {
    
    public static <T> List<List<T>> generatePermutations(List<T> input) {
        List<List<T>> result = new ArrayList<>();
        backtrack(result, input, new ArrayList<>());
        return result;
    }

    private static <T> void backtrack(List<List<T>> result, List<T> input, List<T> subResult) {
        if (subResult.size() == input.size()) {
            result.add(new ArrayList<>(subResult));
        }
        // Generates permutations of set {} digits
        for (T item : input) {
            if (subResult.contains(item)) continue;
            subResult.add(item);
            backtrack(result, input, subResult);
            subResult.remove(item);
        }
    }

    public static void main(String[] args) {
        List<List<String>> permutations = generatePermutations(List.of("a", "b", "c", "d"));
        permutations.forEach(a -> System.out.println(a));
        System.out.println("Amount: " + permutations.size());
    }
}
