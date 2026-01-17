package Practice;

import java.util.*;

public class MergeIntervalsSolution {

    public static List<List<Integer>> merge(List<List<Integer>> intervals) {
        // Step 1: Sort intervals based on start time
        intervals.sort((a, b) -> a.get(0) - b.get(0));

        List<List<Integer>> merged = new ArrayList<>();

        for (List<Integer> interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1).get(1) < interval.get(0)) {
                // No overlap → add new interval
                merged.add(new ArrayList<>(interval));
            } else {
                // Overlapping → update end of last interval
                List<Integer> last = merged.get(merged.size() - 1);
                last.set(1, Math.max(last.get(1), interval.get(1)));
            }
        }

        return merged;
    }

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(1, 3));
        input.add(Arrays.asList(2, 6));
        input.add(Arrays.asList(8, 10));
        input.add(Arrays.asList(15, 18));

        List<List<Integer>> result = merge(input);

        System.out.println("Merged Intervals:");
        for (List<Integer> interval : result) {
            System.out.println(interval);
        }
    }
}

