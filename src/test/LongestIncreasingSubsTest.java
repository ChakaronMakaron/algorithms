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

        Integer[] seq7 = {50, 3, 10, 7, 40, 80, 14, 19, 99, 6, 1, 25, 7, 2, 56, 2};

        assertEquals(5, longestIncSubsRecursive(seq1, 0, seq1.length, Integer.MIN_VALUE));
        assertEquals(1, longestIncSubsRecursive(seq2, 0, seq2.length, Integer.MIN_VALUE));
        assertEquals(7, longestIncSubsRecursive(seq3, 0, seq3.length, Integer.MIN_VALUE));
        assertEquals(2, longestIncSubsRecursive(seq4, 0, seq4.length, Integer.MIN_VALUE));
        assertEquals(3, longestIncSubsRecursive(seq5, 0, seq5.length, Integer.MIN_VALUE));
        assertEquals(1, longestIncSubsRecursive(seq6, 0, seq6.length, Integer.MIN_VALUE));
        assertEquals(6, longestIncSubsRecursive(seq7, 0, seq7.length, Integer.MIN_VALUE));
    }
}
