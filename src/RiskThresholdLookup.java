import java.util.*;

/**
 * Problem 6: Risk Threshold Binary Lookup
 */
public class RiskThresholdLookup {

    /**
     * Linear search for exact threshold in unsorted array
     */
    public static void linearSearch(int[] risks, int target) {
        int comparisons = 0;
        boolean found = false;
        for (int r : risks) {
            comparisons++;
            if (r == target) {
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("Linear: threshold=" + target + " → found (" + comparisons + " comparisons)");
        } else {
            System.out.println("Linear: threshold=" + target + " → not found (" + comparisons + " comparisons)");
        }
    }

    /**
     * Binary search for floor (largest <= target)
     */
    public static int binaryFloor(int[] sortedRisks, int target) {
        int low = 0, high = sortedRisks.length - 1;
        int floor = Integer.MIN_VALUE;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (sortedRisks[mid] == target) {
                floor = sortedRisks[mid];
                break;
            } else if (sortedRisks[mid] < target) {
                floor = sortedRisks[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary floor(" + target + "): " + (floor == Integer.MIN_VALUE ? "N/A" : floor)
                + " (" + comparisons + " comparisons)");
        return floor;
    }

    /**
     * Binary search for ceiling (smallest >= target)
     */
    public static int binaryCeiling(int[] sortedRisks, int target) {
        int low = 0, high = sortedRisks.length - 1;
        int ceiling = Integer.MAX_VALUE;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (sortedRisks[mid] == target) {
                ceiling = sortedRisks[mid];
                break;
            } else if (sortedRisks[mid] < target) {
                low = mid + 1;
            } else {
                ceiling = sortedRisks[mid];
                high = mid - 1;
            }
        }

        System.out.println("Binary ceiling(" + target + "): " + (ceiling == Integer.MAX_VALUE ? "N/A" : ceiling)
                + " (" + comparisons + " comparisons)");
        return ceiling;
    }

    /**
     * MAIN METHOD
     */
    public static void main(String[] args) {

        int[] unsortedRisks = {50, 10, 100, 25};
        int[] sortedRisks = unsortedRisks.clone();
        Arrays.sort(sortedRisks);

        int target = 30;

        System.out.println("Unsorted risks: " + Arrays.toString(unsortedRisks));
        System.out.println("Sorted risks: " + Arrays.toString(sortedRisks));

        // Linear search on unsorted array
        linearSearch(unsortedRisks, target);

        // Binary search on sorted array for floor and ceiling
        binaryFloor(sortedRisks, target);
        binaryCeiling(sortedRisks, target);
    }
}