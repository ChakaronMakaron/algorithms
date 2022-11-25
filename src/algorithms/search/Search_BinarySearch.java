package algorithms.search;

import java.util.List;

public class Search_BinarySearch {
    
    public static int binarySearch(List<Integer> list, int key) {
        int upper = list.size() - 1;
        int lower = 0;

        while (lower <= upper) {
            int i = (lower + upper) / 2;

            if (list.get(i) == key) {
                return i;
            }
            else if (list.get(i) > key) {
                upper = i - 1;
            }
            else if (list.get(i) < key) {
                lower = i + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(binarySearch(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 10));
    }
}
