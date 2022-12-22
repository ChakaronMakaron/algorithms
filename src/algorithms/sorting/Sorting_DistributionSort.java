package algorithms.sorting;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Not done
public class Sorting_DistributionSort {
    
    public static List<String> distributionSort(List<String> list) {
        return doJob(list, 0);
    }

    private static List<String> doJob(List<String> list, int charIdx) {
        Map<Character, List<String>> map = new LinkedHashMap<>();

        List<String> outOfComparison = list.stream().filter(str -> (str.length() - 1) < charIdx).collect(Collectors.toList());
        List<String> toCompare = list.stream().filter(str -> (str.length() - 1) >= charIdx).collect(Collectors.toList());

        toCompare.stream()
            .forEach(str -> {
                if (isNull(map.get(str.charAt(charIdx)))) {
                    map.put(str.charAt(charIdx), new ArrayList<>());
                }
                map.get(str.charAt(charIdx)).add(str);
            });
        
        map.forEach((key, val) -> {
                map.put(key, doJob(val, charIdx + 1));
            });

        return Stream.concat(Stream.of(outOfComparison), map.values().stream())
            .flatMap(item -> item.stream())
            .collect(Collectors.toList());
    }
}
