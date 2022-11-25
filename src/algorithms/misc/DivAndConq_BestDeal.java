package algorithms.misc;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DivAndConq_BestDeal {
    
    public static int trade(List<Integer> prices) {
        if (prices.size() == 1) return 0;
        List<Integer> former = prices.stream().limit(prices.size() / 2).collect(Collectors.toList());
        List<Integer> latter = prices.stream().skip(prices.size() / 2).collect(Collectors.toList());
        int currentMax = Collections.max(latter) - Collections.min(former);
        return Collections.max(List.of(trade(former), trade(latter), currentMax));
    }

    public static void main(String[] args) {
        System.out.println(trade(new Random().ints(30, 1, 50).boxed().collect(Collectors.toList())));
    }
}
