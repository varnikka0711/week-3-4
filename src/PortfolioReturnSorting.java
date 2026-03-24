import java.util.*;

/**
 * Problem 4: Portfolio Return Sorting
 */
public class PortfolioReturnSorting {

    /**
     * CLASS - Asset
     */
    static class Asset {
        String symbol;
        double returnRate;   // in %
        double volatility;   // standard deviation or risk measure

        public Asset(String symbol, double returnRate, double volatility) {
            this.symbol = symbol;
            this.returnRate = returnRate;
            this.volatility = volatility;
        }

        @Override
        public String toString() {
            return symbol + ":" + returnRate + "%";
        }

        public String detailed() {
            return symbol + ":" + returnRate + "% (Vol:" + volatility + ")";
        }
    }

    /**
     * Merge Sort (Ascending by returnRate, stable)
     */
    public static void mergeSort(Asset[] arr) {
        if (arr.length <= 1) return;
        Asset[] temp = new Asset[arr.length];
        mergeSortHelper(arr, temp, 0, arr.length - 1);
    }

    private static void mergeSortHelper(Asset[] arr, Asset[] temp, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSortHelper(arr, temp, left, mid);
        mergeSortHelper(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }

    private static void merge(Asset[] arr, Asset[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (arr[i].returnRate <= arr[j].returnRate) {
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
     * Quick Sort (Descending by returnRate, then ascending volatility)
     */
    public static void quickSort(Asset[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortHelper(Asset[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSortHelper(arr, low, pivotIndex - 1);
            quickSortHelper(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high]; // simple pivot, can use median-of-3
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot.returnRate ||
                    (arr[j].returnRate == pivot.returnRate && arr[j].volatility < pivot.volatility)) {
                i++;
                Asset temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Asset temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /**
     * MAIN METHOD
     */
    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12, 0.25),
                new Asset("TSLA", 8, 0.35),
                new Asset("GOOG", 15, 0.20)
        };

        // Merge Sort ascending
        Asset[] mergeArr = assets.clone();
        mergeSort(mergeArr);
        System.out.print("MergeSort (asc): [");
        for (int i = 0; i < mergeArr.length; i++) {
            System.out.print(mergeArr[i]);
            if (i != mergeArr.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        // Quick Sort descending
        Asset[] quickArr = assets.clone();
        quickSort(quickArr);
        System.out.print("QuickSort (desc): [");
        for (int i = 0; i < quickArr.length; i++) {
            System.out.print(quickArr[i]);
            if (i != quickArr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}