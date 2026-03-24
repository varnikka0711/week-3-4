import java.util.*;

/**
 * Problem 3: Historical Trade Volume Analysis
 */
public class TradeVolumeAnalysis {

    /**
     * CLASS - Trade
     */
    static class Trade {
        String id;
        int volume;

        public Trade(String id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        @Override
        public String toString() {
            return id + ":" + volume;
        }
    }

    /**
     * Merge Sort (Ascending, stable)
     */
    public static void mergeSort(Trade[] arr) {
        if (arr.length <= 1) return;
        Trade[] temp = new Trade[arr.length];
        mergeSortHelper(arr, temp, 0, arr.length - 1);
    }

    private static void mergeSortHelper(Trade[] arr, Trade[] temp, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSortHelper(arr, temp, left, mid);
        mergeSortHelper(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }

    private static void merge(Trade[] arr, Trade[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (arr[i].volume <= arr[j].volume) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (i = left; i <= right; i++) arr[i] = temp[i];
    }

    /**
     * Quick Sort (Descending, in-place)
     */
    public static void quickSort(Trade[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortHelper(Trade[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSortHelper(arr, low, pivotIndex - 1);
            quickSortHelper(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(Trade[] arr, int low, int high) {
        Trade pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume >= pivot.volume) { // descending
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /**
     * Merge two sorted trade lists into one ascending order
     */
    public static Trade[] mergeSortedTrades(Trade[] a, Trade[] b) {
        int n = a.length, m = b.length;
        Trade[] merged = new Trade[n + m];
        int i = 0, j = 0, k = 0;

        while (i < n && j < m) {
            if (a[i].volume <= b[j].volume) {
                merged[k++] = a[i++];
            } else {
                merged[k++] = b[j++];
            }
        }

        while (i < n) merged[k++] = a[i++];
        while (j < m) merged[k++] = b[j++];

        return merged;
    }

    /**
     * Compute total volume
     */
    public static int totalVolume(Trade[] trades) {
        int sum = 0;
        for (Trade t : trades) sum += t.volume;
        return sum;
    }

    /**
     * MAIN METHOD
     */
    public static void main(String[] args) {

        // Sample trades
        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        // Merge Sort ascending
        Trade[] mergeArr = trades.clone();
        mergeSort(mergeArr);
        System.out.print("MergeSort: [");
        for (int i = 0; i < mergeArr.length; i++) {
            System.out.print(mergeArr[i]);
            if (i != mergeArr.length - 1) System.out.print(", ");
        }
        System.out.println("] // Stable");

        // Quick Sort descending
        Trade[] quickArr = trades.clone();
        quickSort(quickArr);
        System.out.print("QuickSort (desc): [");
        for (int i = 0; i < quickArr.length; i++) {
            System.out.print(quickArr[i]);
            if (i != quickArr.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        // Merge morning + afternoon sessions
        Trade[] morning = {
                new Trade("tradeA", 200),
                new Trade("tradeB", 400)
        };
        Trade[] afternoon = {
                new Trade("tradeC", 150),
                new Trade("tradeD", 300)
        };
        mergeSort(morning);
        mergeSort(afternoon);
        Trade[] mergedDay = mergeSortedTrades(morning, afternoon);

        System.out.print("Merged morning+afternoon: [");
        for (int i = 0; i < mergedDay.length; i++) {
            System.out.print(mergedDay[i]);
            if (i != mergedDay.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        int totalVol = totalVolume(mergedDay);
        System.out.println("Total volume post-merge: " + totalVol);
    }
}