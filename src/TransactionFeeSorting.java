import java.util.*;

/**
 * Problem 1: Transaction Fee Sorting for Audit Compliance
 */
public class TransactionFeeSorting {

    /**
     * CLASS - Transaction
     */
    static class Transaction {
        String id;
        double fee;
        String timestamp;

        public Transaction(String id, double fee, String timestamp) {
            this.id = id;
            this.fee = fee;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return id + ":" + fee;
        }

        public String detailed() {
            return id + ":" + fee + "@" + timestamp;
        }
    }

    /**
     * Bubble Sort (Stable)
     */
    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // early termination
        }

        System.out.print("BubbleSort (fees): [");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i != list.size() - 1) System.out.print(", ");
        }
        System.out.println("] // " + passes + " passes, " + swaps + " swaps");
    }

    /**
     * Insertion Sort (Stable)
     * Sort by fee, then timestamp
     */
    public static void insertionSort(List<Transaction> list) {

        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 &&
                    (list.get(j).fee > key.fee ||
                            (list.get(j).fee == key.fee &&
                                    list.get(j).timestamp.compareTo(key.timestamp) > 0))) {

                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }

        System.out.print("InsertionSort (fee+ts): [");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).detailed());
            if (i != list.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    /**
     * High-fee outlier detection
     */
    public static void findOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();

        for (Transaction t : list) {
            if (t.fee > 50) {
                outliers.add(t);
            }
        }

        System.out.print("High-fee outliers: ");
        if (outliers.isEmpty()) {
            System.out.println("none");
        } else {
            for (Transaction t : outliers) {
                System.out.print(t + " ");
            }
            System.out.println();
        }
    }

    /**
     * MAIN METHOD
     */
    public static void main(String[] args) {

        // Sample Input
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        // Clone lists for independent sorting
        List<Transaction> bubbleList = new ArrayList<>(transactions);
        List<Transaction> insertionList = new ArrayList<>(transactions);

        // Bubble Sort
        bubbleSort(bubbleList);

        // Insertion Sort
        insertionSort(insertionList);

        // Outlier Detection
        findOutliers(transactions);
    }
}