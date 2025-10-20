package io.examples.sort;
/**
 * Dieses Modul enthält mehrere Sortieralgorithmen die dazu geschrieben sind um Tests
 * zu kreieren welche zeigen wie man in Java UNIT Testing betreiben kann.
 * @author: Harald Glab-Plhak
 */

/*Die notwendigen Importstatements */
import java.util.Arrays;
import static io.examples.sort.SortData.*;

/**
 *
 * @param <T> future use
 */
public class QBubbleMerge<T extends Comparable<T>> {

    /** NO Instance all static */
    private QBubbleMerge() {

    }

    /**
     * Enumeration fuer die Sortierung aufsteigend(ASC) / absteigend(DESC)
     */
    public enum SortDirection {
        DEFAULT , DESC , ASC ,
        ;}


    /**
     * Funktion zum tauschen von Werten a[i](5), b[i](8) -->  a[i](8), b[i](5)
     * @param arr der Array innerhalb dessen getauscht wird
     * @param from von Quelle
     * @param to nach Ziel
     */
    public static void swap(Integer [] arr, Integer from, Integer to) {
        Integer temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

    /**
     * Funktion zur Realisierung eines QuickSort
     * @param arr the array to sort
     * @param direction the sort direction
     * @param begin Beginn Index
     * @param end Ende Index
     * @return sortierter Array
     */
    public static void quickSort(Integer [] arr, SortDirection direction, Integer begin, Integer end) {
        if (begin <= end) {
            int partitionIndex = QBubbleMerge.partition(arr, direction, begin, end);
            quickSort(arr, direction, begin, partitionIndex - 1);
            quickSort(arr, direction, partitionIndex + 1, end);
        }
    }
/**
 * Now we need to show also this function to see what happens *
 */

    /**
     *
     * @param arr
     * @param direction
     * @param begin
     * @param end
     * @return
     */
    private static Integer partition(Integer [] arr, SortDirection direction,
        Integer begin, Integer end) {
        int pivot = arr[end];
        int i = (begin - 1);
        for (int j = begin; j <= (end - 1); j++) {
            boolean swapit = false;
            switch (direction) {
                case ASC -> swapit = (arr[j].compareTo(pivot) < 0);
                case DESC -> swapit = (arr[j].compareTo(pivot) > 0);
                case DEFAULT -> swapit = Boolean.FALSE;
            }
            if (swapit) {
                i++;
                swap(arr, i , j);
            }
        }
        swap(arr, i + 1, end);
        return ++i;
    }

    /**
     *
     * @param arr
     * @param direction
     * @param last_offset
     * @return
     */

    public static void bubbleSort(Integer[] arr, SortDirection direction, Integer last_offset) {
        boolean swapped;

        for (int indexI = 0; indexI <= last_offset; indexI++) {
            swapped = false;
            for (int indexJ = 0; indexJ < (last_offset - indexI); indexJ++) {
                if ((direction.equals(SortDirection.ASC)
                        && arr[indexJ] > arr[indexJ + 1]) ||
                        (direction.equals(SortDirection.DESC)
                                && arr[indexJ] < arr[indexJ + 1])) {
                    swap(arr, indexJ, (indexJ + 1));
                    swapped = true;
                }
            }

            // If no two elements were swapped, then break
            if (!swapped)
                break;
        }
    }

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    static void merge(Integer arr[], SortDirection direction, int l, int m, int r){

        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        Boolean swap = Boolean.FALSE;
        while (i < n1 && j < n2) {

            swap = ((L[i] <= R[j]) && direction.equals(SortDirection.ASC));
            if (!swap) {
                swap = ((L[i] >= R[j]) && direction.equals(SortDirection.DESC));
            }


            if (swap) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    static void mergeSort(Integer arr[], SortDirection direction, int l, int r){

        if (l < r) {

            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergeSort(arr, direction, l, m);
            mergeSort(arr, direction, m + 1, r);

            // Merge the sorted halves
            merge(arr, direction, l, m, r);
        }
    }

    public static void main(String[] args) {
        Integer [] array = SORT_DATA.clone();
        quickSort(array, SortDirection.DESC, 0, LAST_INDEX);
        System.out.println("-> DESCEND ARR QSORT =============================================================");
        Arrays.stream(array).forEach(e -> System.out.printf("element (%d) \n", e));
        array = SORT_DATA.clone();
        quickSort(array, SortDirection.ASC, 0, LAST_INDEX);
        System.out.println("-> ASCEND ARR QSORT ==============================================================");
        Arrays.stream(array).forEach(e -> System.out.printf("element (%d) \n", e));
        System.out.println("-> FINISH");
        array = SORT_DATA.clone();
        bubbleSort(array, SortDirection.DESC, LAST_INDEX);
        System.out.println("-> DESCEND ARR BUBBLE =============================================================");
        Arrays.stream(array).forEach(e -> System.out.printf("element (%d) \n", e));
        array = SORT_DATA.clone();
        bubbleSort(array, SortDirection.ASC, LAST_INDEX);
        System.out.println("-> ASCEND ARR BUBBLE ==============================================================");
        Arrays.stream(array).forEach(e -> System.out.printf("element (%d) \n", e));
        System.out.println("-> FINISH");
        array = SORT_DATA.clone();
        mergeSort(array, SortDirection.ASC, 0, LAST_INDEX);
        System.out.println("-> MERGE SORT ASC ==============================================================");
        Arrays.stream(array).forEach(e -> System.out.printf("element (%d) \n", e));
        array = SORT_DATA.clone();
        mergeSort(array, SortDirection.DESC, 0, LAST_INDEX);
        System.out.println("-> MERGE SORT DESC ==============================================================");
        Arrays.stream(array).forEach(e -> System.out.printf("element (%d) \n", e));
        System.out.println("-> FINISH");
    }
}
