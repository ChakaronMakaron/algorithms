package algorithms.dynamic.programming;

import java.util.Arrays;
import java.util.Collections;

public class DynamicProgramming_LongestIncreasingSubs {
    
    public static int longestIncSubsRecursive(Integer[] sequence, int n) {
        System.out.println(Arrays.toString(sequence));
        if (n == sequence.length - 1) {
            if (sequence[n] >= sequence[n - 1]) {
                return 2;
            }
            return 1;
        }

        Integer[] res = new Integer[sequence.length];
        Arrays.fill(res, 0);

        System.out.println("i: " + sequence[n]);
        
        for (int i = n; i < res.length; i++) {
            int increment = 0;
            if (sequence[n - 1] <= sequence[n]) {
                increment++;
                System.out.println("i-1: " + sequence[n - 1]);
                
            }
            res[n - 1] = longestIncSubsRecursive(sequence, n + 1) + increment;
        }
            
        return Collections.max(Arrays.asList(res));
    }

    public static void main(String[] args) {
        Integer[] seq = {2, 4, 3, 5, 1, 7, 6, 9, 8};
        //Integer[] seq = {2, 3, 5, 6, 7, 8};

        System.out.println(longestIncSubsRecursive(seq, 1));
    }
}
