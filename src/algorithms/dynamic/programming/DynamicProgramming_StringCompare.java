package algorithms.dynamic.programming;

import static java.util.Collections.min;

import java.util.List;

public class DynamicProgramming_StringCompare {
 
    static int editDist(String str1, String str2, int m, int n) {
        if (m == 0) return n;
        if (n == 0) return m;

        if (str1.charAt(m) == str2.charAt(n))
            return editDist(str1, str2, m - 1, n - 1);
        
        return 1 + min(List.of(
            editDist(str1, str2, m, n - 1),
            editDist(str1, str2, m - 1, n),
            editDist(str1, str2, m - 1, n - 1)
        ));
    }

    public static void main(String args[])
    {
        String str1 = "sunday";
        String str2 = "saturday";
 
        System.out.println(editDist(
            str1, str2, str1.length() - 1, str2.length() - 1));
    }
}
