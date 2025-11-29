from enum import Enum




class QBubbleMergeSort:
    SortOrder = Enum('SortOrder', [('ASC', 1), ('DESC', 2), ('DEFAULT', 3)])

    def __init__(self):
        print("blah")

    @staticmethod
    def partition(sortorder, arr, low, high):
        # choose the pivot
        pivot = arr[high]

        # index of smaller element and indicates
        # the right position of pivot found so far
        i = low - 1

        # traverse arr[low..high] and move all smaller
        # elements to the left side. Elements from low to
        # i are smaller after every iteration
        for j in range(low, high):
            if sortorder == QBubbleMergeSort.SortOrder.ASC \
                    or sortorder == QBubbleMergeSort.SortOrder.DEFAULT:
                if sortorder == QBubbleMergeSort.SortOrder.DESC:
                    if arr[j] < pivot:
                        i += 1
                        QBubbleMergeSort.swap(arr, i, j)
            else:
                if arr[j] < pivot:
                    i += 1
                    QBubbleMergeSort.swap(arr, i, j)

        # move pivot after smaller elements and
        # return its position
        QBubbleMergeSort.swap(arr, i + 1, high)
        return i + 1

    # swap function
    @staticmethod
    def swap(arr, i, j):
        arr[i], arr[j] = arr[j], arr[i]

    # the QuickSort function implementation
    @staticmethod
    def quickSort(sortorder, arr, low, high):
        if low < high:
            # pi is the partition return index of pivot
            pi = QBubbleMergeSort.partition(sortorder, arr, low, high)

            # recursion calls for smaller elements
            # and greater or equals elements
            QBubbleMergeSort.quickSort(sortorder, arr, low, pi - 1)
            QBubbleMergeSort.quickSort(sortorder, arr, pi + 1, high)


    @staticmethod
    def bubbleSort(sortorder, arr):
        n = len(arr)

    # Traverse through all array elements
        for i in range(n):
            swapped = False

        # Last i elements are already in place
            for j in range(0, n - i - 1):

            # Traverse the array from 0 to n-i-1
            # Swap if the element found is greater
            # than the next element
                if sortorder == QBubbleMergeSort.SortOrder.ASC \
                    or sortorder == QBubbleMergeSort.SortOrder.DEFAULT:
                    if arr[j] > arr[j + 1]:
                        QBubbleMergeSort.swap(arr, j, j + 1)
                        swapped = True
                else:
                    if sortorder == QBubbleMergeSort.SortOrder.DESC:
                        if arr[j] < arr[j + 1]:
                            QBubbleMergeSort.swap(arr, j, j + 1)
                            swapped = True
            if swapped:
                continue
            else:
                break


    @staticmethod


    def merge(arr, left, mid, right):
        n1 = mid - left + 1
        n2 = right - mid

    # Create temp arrays
        L = [0] * n1
        R = [0] * n2

    # Copy data to temp arrays L[] and R[]
        for i in range(n1):
            L[i] = arr[left + i]
        for j in range(n2):
            R[j] = arr[mid + 1 + j]

        i = 0
        j = 0
        k = left

        # Merge the temp arrays back
        # into arr[left..right]
        while i < n1 and j < n2:
            if L[i] <= R[j]:
                arr[k] = L[i]
                i += 1
            else:
                arr[k] = R[j]
                j += 1
            k += 1

    # Copy the remaining elements of L[],
    # if there are any
        while i < n1:
            arr[k] = L[i]
            i += 1
            k += 1

    # Copy the remaining elements of R[],
    # if there are any
        while j < n2:
            arr[k] = R[j]
            j += 1
            k += 1


    @staticmethod
    def mergeSort(arr, left, right):
        if left < right:
            mid = (left + right) // 2
            QBubbleMergeSort.mergeSort(arr, left, mid)
            QBubbleMergeSort.mergeSort(arr, mid + 1, right)
            QBubbleMergeSort.merge(arr, left, mid, right)
