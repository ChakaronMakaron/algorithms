package algorithms.dynamic.programming;

import java.util.HashMap;
import java.util.Map;

public class DynamicProgramming_Fibonacci {
    
    private static Map<Long, Long> memo = new HashMap<>();

    public static long fib(long n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static long fibOptimized(long n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (memo.get(n) != null) {
            return memo.get(n);
        }
        memo.put(n, fibOptimized(n - 1) + fibOptimized(n - 2));
        return memo.get(n);
    }

    public static void main(String[] args) {
        // System.out.println(fib(45));
        System.out.println(fibOptimized(60));
    }
}
