package algorithms.dynamic.programming;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicProgramming_Change {
    
    public static int changeRecursive(List<Integer> bills, int depth, int startSum, int target) {
        if (startSum > target) return Integer.MAX_VALUE;
        if (startSum == target) return depth;

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < bills.size(); i++) {
            int res = changeRecursive(bills, depth + 1, startSum + bills.get(i), target);
            if (res < min) min = res;
        }

        return min;
    }

    public static void main(String[] args) {
        //System.out.println(changeRecursive(List.of(5, 10, 20), 0, 0, 150));
        System.out.println(changeRecursiveOptimized(List.of(5, 10, 20), 500, new HashMap<>()));
        //System.out.println(Math.min(Integer.MAX_VALUE, 1));
    }

    public static int changeRecursiveOptimized(List<Integer> bills, int amount, Map<Integer, Integer> memo) {
        if (amount == 0) return 0;
        if (amount < 0) return Integer.MAX_VALUE - 1;
        if (memo.get(amount) != null) return memo.get(amount);

        int min = Integer.MAX_VALUE;

        for (Integer billValue : bills) {
            int rest = amount - billValue;
            min = Math.min(changeRecursiveOptimized(bills, rest, memo) + 1, min);
        }

        memo.put(amount, min);
        return memo.get(amount);
    }
}
