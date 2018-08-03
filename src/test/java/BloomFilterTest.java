import org.junit.Test;

import java.util.*;


public class BloomFilterTest {
    @Test
    public void testBloomFilter() {
        double falsePositiveProbability = 0.1;
        int expectedSize = 100000;

        BloomFilter<Integer> bloomFilter = new BloomFilter<Integer>(falsePositiveProbability, expectedSize);

        List<Integer> trades = new ArrayList<Integer>();
        List<Integer> finalizedTrades = new ArrayList<Integer>();
        List<Integer> tradesToBeLoaded = new ArrayList<Integer>();

        for (int i = 1; i < 1000; i++) {
            finalizedTrades.add(i);
            bloomFilter.add(i);
        }

        for (int i = 1; i < 1000000; i++) {
            trades.add(i);
        }

        for (Integer i : trades) {
            if (!bloomFilter.contains(i)) {
                tradesToBeLoaded.add(i);
            }
        }

        if (bloomFilter.contains(100)) { // Always returns true
            System.out.println("BloomFilter contains 100!");
            System.out.println("Probability of a false positive: " + bloomFilter.expectedFalsePositiveProbability());
        }

        if (bloomFilter.contains(-10)) { // Should return false, but could return true
            System.out.println("There was a false positive.");
        }
    }
}