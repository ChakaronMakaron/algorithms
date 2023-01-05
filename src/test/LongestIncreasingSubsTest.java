package test;

import static algorithms.dynamic.programming.DynamicProgramming_LongestIncreasingSubs.longestIncSubsRecursive;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestIncreasingSubsTest {
    
    @Test
    public void test() {

        Integer[] seq1 = {2, 4, 3, 5, 1, 7, 6, 9, 8};

        Integer[] seq2 = {1, 0};

        Integer[] seq3 = {2, 3, 5, 6, 7, 8, 9};

        Integer[] seq4 = {5, 4, 1, 9, 8, 7};

        Integer[] seq5 = {4, 10, 4, 3, 8, 9};

        Integer[] seq6 = {4};

        assertEquals(5, longestIncSubsRecursive(seq1, seq1.length - 1));
        assertEquals(1, longestIncSubsRecursive(seq2, seq2.length - 1));
        assertEquals(7, longestIncSubsRecursive(seq3, seq3.length - 1));
        assertEquals(2, longestIncSubsRecursive(seq4, seq4.length - 1));
        assertEquals(3, longestIncSubsRecursive(seq5, seq5.length - 1));
        assertEquals(1, longestIncSubsRecursive(seq6, seq6.length - 1));
    }
}
