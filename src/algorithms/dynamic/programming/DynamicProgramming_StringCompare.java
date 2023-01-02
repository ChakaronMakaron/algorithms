package algorithms.dynamic.programming;

import static java.util.Collections.min;

import java.util.Arrays;
import java.util.List;

public class DynamicProgramming_StringCompare {
 
    public static int editDistRecursive(String str1, String str2, int m, int n) {
        if (m == 0) return n;
        if (n == 0) return m;

        if (str1.charAt(m - 1) == str2.charAt(n - 1))
            return editDistRecursive(str1, str2, m - 1, n - 1);
        
        return 1 + min(List.of(
            editDistRecursive(str1, str2, m, n - 1),
            editDistRecursive(str1, str2, m - 1, n),
            editDistRecursive(str1, str2, m - 1, n - 1)
        ));
    }

    public static int editDistDynamic(String str1, String str2) {
        str1 = " " + str1;
        str2 = " " + str2;

        // Memo init
        int[][] memo = new int[str1.length()][str2.length()];
        for (int i = 0; i < memo.length; i++) {
            memo[i][0] = i;
        }
        for (int j = 0; j < memo[0].length; j++) {
            memo[0][j] = j;
        }

        for (int i = 1; i < str1.length(); i++) {
            for (int j = 1; j < str2.length(); j++) {
                memo[i][j] = min(List.of(
                    memo[i - 1][j - 1] + (str1.charAt(i) == str2.charAt(j) ? 0 : 1),
                    memo[i - 1][j] + 1,
                    memo[i][j - 1] + 1
                ));
                printArr(memo);
                System.out.println("----------------------");
            }
        }

        return memo[memo.length - 1][memo[0].length - 1];
    }

    private static void printArr(int[][] arr) {
        for (int[] is : arr) {
            System.out.println(Arrays.toString(is));
        }
    }

    public static void main(String args[]) {
        String str1 = "you should not";
        String str2 = "thou shalt not";

        System.out.println(editDistRecursive(str1, str2, str1.length(), str2.length()));
    }
}
