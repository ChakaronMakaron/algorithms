package algorithms.dynamic.programming;

import static java.util.Arrays.asList;
import static java.util.Collections.max;

import java.util.Arrays;
import java.util.Collections;

public class DynamicProgramming_LongestIncreasingSubs {
    
    public static int longestIncSubsRecursive(Integer[] sequence, int n) {
        if (n == 0) {return 1;}

        Integer[] table = new Integer[sequence.length];
        Arrays.fill(table, 0);

        for (int i = n - 1; i > 0; i--) {
            table[i] = longestIncSubsRecursive(sequence, i);
            if (sequence[n] > sequence[i]) {
                table[i] = table[i] + 1;
            }
        }

        return max(asList(table));
    }

    public static void main(String[] args) {
        //Integer[] seq = {2, 4, 3, 5, 1, 7, 6, 9, 8};
        //Integer[] seq2 = {1, 0};
        //Integer[] seq1 = {2, 3, 5, 6, 7, 8, 9};
        Integer[] seq3 = {5, 4, 1, 9, 8, 7};

        Integer[] seq44 = {4,10,4,3,8,9};

        System.out.println(longestIncSubsRecursive(seq44, seq44.length - 1));
    }
}
