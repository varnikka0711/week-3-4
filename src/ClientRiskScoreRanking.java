import java.util.*;

/**
 * Problem 2: Client Risk Score Ranking
 */
public class ClientRiskScoreRanking {

    /**
     * CLASS - Client
     */
    static class Client {
        String name;
        int riskScore;
        double accountBalance;

        public Client(String name, int riskScore, double accountBalance) {
            this.name = name;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        @Override
        public String toString() {
            return name + ":" + riskScore;
        }

        public String detailed() {
            return name + ":" + riskScore + " (Bal:" + accountBalance + ")";
        }
    }

    /**
     * Bubble Sort (Ascending by riskScore)
     */
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break; // early termination
        }

        System.out.print("Bubble (asc): [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) System.out.print(", ");
        }
        System.out.println("] // Swaps: " + swaps);
    }

    /**
     * Insertion Sort (Descending by riskScore, then accountBalance)
     */
    public static void insertionSort(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;
            while (j >= 0 &&
                    (arr[j].riskScore < key.riskScore ||
                            (arr[j].riskScore == key.riskScore &&
                                    arr[j].accountBalance < key.accountBalance))) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        System.out.print("Insertion (desc): [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    /**
     * Print top N highest risk clients
     */
    public static void printTopRisks(Client[] arr, int topN) {
        System.out.print("Top " + topN + " risks: ");
        for (int i = 0; i < Math.min(topN, arr.length); i++) {
            System.out.print(arr[i].name + "(" + arr[i].riskScore + ")");
            if (i != topN - 1 && i != arr.length - 1) System.out.print(", ");
        }
        System.out.println();
    }

    /**
     * MAIN METHOD
     */
    public static void main(String[] args) {

        // Sample input: small demo (replace with 500 for actual use)
        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        // Clone arrays for independent sorting
        Client[] bubbleArr = clients.clone();
        Client[] insertionArr = clients.clone();

        // Bubble Sort ascending
        bubbleSort(bubbleArr);

        // Insertion Sort descending
        insertionSort(insertionArr);

        // Top 10 risks
        printTopRisks(insertionArr, 3); // for demo, top 3
    }
}