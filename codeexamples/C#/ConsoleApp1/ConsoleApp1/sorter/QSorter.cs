namespace ConsoleApp1.sorter;

public class QSorter
{
    // partition function
    static int partition(int[] arr, int low, int high) {
        
        // choose the pivot
        var pivot = arr[high];
        
        // index of smaller element and indicates 
        // the right position of pivot found so far
        var i = low - 1;

        // traverse arr[low..high] and move all smaller
        // elements to the left side. Elements from low to 
        // i are smaller after every iteration
        for (var j = low; j <= high - 1; j++)
        {
            if (arr[j] >= pivot) continue;
            i++;
            swap(arr, i, j);
        }
        
        // move pivot after smaller elements and
        // return its position
        swap(arr, i + 1, high);  
        return i + 1;
    }

    // swap function
    static void swap(int[] arr, int i, int j) {
        (arr[i], arr[j]) = (arr[j], arr[i]);
    }

    // The QuickSort function implementation
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            
            // pi is the partition return index of pivot
            int pi = partition(arr, low, high);

            // recursion calls for smaller elements
            // and greater or equals elements
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    
        }
    

