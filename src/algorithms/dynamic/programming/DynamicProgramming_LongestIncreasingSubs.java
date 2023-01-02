package algorithms.dynamic.programming;

import static java.util.Arrays.asList;
import static java.util.Collections.max;

import java.util.Arrays;

public class DynamicProgramming_LongestIncreasingSubs {
    
    public static int longestIncSubsRecursive(Integer[] sequence, int n) {
        if (n == sequence.length - 1) {
            if (sequence[n] > sequence[n - 1]) {
                return 2;
            }
            return 1;
        }

        Integer[] res = new Integer[sequence.length];
        Arrays.fill(res, 0);
        
        for (int i = n; i < res.length; i++) {
            int increment = sequence[n - 1] < sequence[n] ? 1 : 0;
            res[n - 1] = longestIncSubsRecursive(sequence, n + 1) + increment;
        }
            
        return max(asList(res));
    }

    public static void main(String[] args) {
        Integer[] seq = {2, 4, 3, 5, 1, 7, 6, 9, 8};
        //Integer[] seq2 = {1, 0};
        //Integer[] seq1 = {2, 3, 5, 6, 7, 8};

        System.out.println(longestIncSubsRecursive(seq, 1));
    }
}
