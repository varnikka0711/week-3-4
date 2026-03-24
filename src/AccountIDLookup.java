import java.util.*;

/**
 * Problem 5: Account ID Lookup in Transaction Logs
 */
public class AccountIDLookup {

    /**
     * Linear Search for first occurrence
     */
    public static int linearFirst(String[] logs, String target) {
        int comparisons = 0;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                System.out.println("Linear first '" + target + "': index " + i + " (" + comparisons + " comparisons)");
                return i;
            }
        }
        System.out.println("Linear first '" + target + "': not found (" + comparisons + " comparisons)");
        return -1;
    }

    /**
     * Linear Search for last occurrence
     */
    public static int linearLast(String[] logs, String target) {
        int comparisons = 0;
        int lastIndex = -1;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                lastIndex = i;
            }
        }
        if (lastIndex != -1) {
            System.out.println("Linear last '" + target + "': index " + lastIndex + " (" + comparisons + " comparisons)");
        } else {
            System.out.println("Linear last '" + target + "': not found (" + comparisons + " comparisons)");
        }
        return lastIndex;
    }

    /**
     * Binary Search for first occurrence (sorted logs)
     */
    public static int binaryFirst(String[] logs, String target) {
        int low = 0, high = logs.length - 1, comparisons = 0;
        int result = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            int cmp = logs[mid].compareTo(target);
            if (cmp == 0) {
                result = mid;
                high = mid - 1; // continue searching left
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (result != -1) {
            System.out.println("Binary first '" + target + "': index " + result + " (" + comparisons + " comparisons)");
        } else {
            System.out.println("Binary first '" + target + "': not found (" + comparisons + " comparisons)");
        }
        return result;
    }

    /**
     * Binary Search for last occurrence (sorted logs)
     */
    public static int binaryLast(String[] logs, String target) {
        int low = 0, high = logs.length - 1, comparisons = 0;
        int result = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            int cmp = logs[mid].compareTo(target);
            if (cmp == 0) {
                result = mid;
                low = mid + 1; // continue searching right
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (result != -1) {
            System.out.println("Binary last '" + target + "': index " + result + " (" + comparisons + " comparisons)");
        } else {
            System.out.println("Binary last '" + target + "': not found (" + comparisons + " comparisons)");
        }
        return result;
    }

    /**
     * Count occurrences in sorted array
     */
    public static int countOccurrences(String[] sortedLogs, String target) {
        int first = binaryFirst(sortedLogs, target);
        if (first == -1) return 0;
        int last = binaryLast(sortedLogs, target);
        return last - first + 1;
    }

    /**
     * MAIN METHOD
     */
    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};
        String target = "accB";

        // Linear search
        linearFirst(logs, target);
        linearLast(logs, target);

        // Sort logs for binary search
        String[] sortedLogs = logs.clone();
        Arrays.sort(sortedLogs);
        System.out.println("Sorted logs: " + Arrays.toString(sortedLogs));

        // Binary search
        int firstIndex = binaryFirst(sortedLogs, target);
        int count = countOccurrences(sortedLogs, target);
        System.out.println("Binary '" + target + "': first index=" + firstIndex + ", count=" + count);
    }
}