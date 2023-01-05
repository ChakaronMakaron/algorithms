package algorithms.dynamic.programming;

public class DynamicProgramming_LongestIncreasingSubs {
    
    
    public static int longestIncSubsRecursive(Integer[] sequence, int i, int n, int prev) {
        if (i == n) return 0;

        int exclude = longestIncSubsRecursive(sequence, i + 1, n, prev);

        int include = 0;
        if (sequence[i] > prev) {
            include = 1 + longestIncSubsRecursive(sequence, i + 1, n, sequence[i]);
        }

        return Math.max(exclude, include);
    }

    public static void main(String[] args) {
        
        System.out.println(longestIncSubsDP(new Integer[] {1, 5, 8, 1, 45, 6, 14, 5, 5}, 9));
    }

    public static int longestIncSubsDP(Integer[] arr, int n) {
        int lis[] = new int[n];
        int i, j, max = 0;

        for (i = 0; i < n; i++)
            lis[i] = 1;

        for (i = 1; i < n; i++)
            for (j = 0; j < i; j++)
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;
        
        for (i = 0; i < n; i++)
            if (max < lis[i])
                max = lis[i];
 
        return max;
    }
}
