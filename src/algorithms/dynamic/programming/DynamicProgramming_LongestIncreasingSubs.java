package algorithms.dynamic.programming;

public class DynamicProgramming_LongestIncreasingSubs {
    
    public static int longestIncSubsRecursive(Integer[] sequence, int n) {
        if (n == 0) {return 1;}

        int max = 1;

        for (int i = n - 1; i >= 0; i--) {
            if (sequence[n] > sequence[i]) {
                int res = longestIncSubsRecursive(sequence, i) + 1;
                if (res > max) max = res;
            }
        }

        return max;
    }
}
