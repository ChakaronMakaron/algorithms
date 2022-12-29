package algorithms.misc;

import java.util.HashSet;
import java.util.Set;

public class Backtracking_SubsetGeneration {
    
    public static Set<Set<Integer>> generateSubsets(int setSize) {
        Set<Set<Integer>> result = new HashSet<>();
        backtrack(result, new HashSet<>(), 1, setSize);
        return result;
    }

    private static void backtrack(Set<Set<Integer>> result, Set<Integer> subResult, int start, int setSize) {
        if (subResult.size() <= setSize) {
            result.add(new HashSet<>(subResult));
        }
        // Generates subsets of set {1...setSize} digits
        for (int num = start; num <= setSize; num++) {
            subResult.add(num);
            backtrack(result, subResult, num + 1, setSize);
            subResult.remove(num);
        }
    }

    public static void main(String[] args) {
        Set<Set<Integer>> result = generateSubsets(4);
        System.out.println("RESULT SIZE: " + result.size());
        System.out.println(result);
    }
}
