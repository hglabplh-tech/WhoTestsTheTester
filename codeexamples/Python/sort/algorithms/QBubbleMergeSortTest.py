import unittest
import copy
from sort.algorithms.QBubbleMergeSort import QBubbleMergeSort
from sort.algorithms.SortData import SORT_DATA
from sort.algorithms.SortData import LAST_INDEX

class QBubbleMergeSortTest(unittest.TestCase):
    def test_quicksort_simple(self):
        toSortAsc = copy.deepcopy(SORT_DATA)
        toSortAsc.sort()
        toSortAscQuick = copy.deepcopy(SORT_DATA)
        QBubbleMergeSort.quickSort(QBubbleMergeSort.SortOrder.ASC,toSortAscQuick, 0, LAST_INDEX)
        self.assertEqual(set(toSortAsc), set(toSortAscQuick))

        # add assertion here

    def test_bubblesort_simple(self):
        toSortAsc = copy.deepcopy(SORT_DATA)
        toSortAsc.sort()
        toSortAscBubble = copy.deepcopy(SORT_DATA)

        QBubbleMergeSort.bubbleSort(QBubbleMergeSort.SortOrder.ASC, toSortAscBubble)
        self.assertEqual(set(toSortAsc), set(toSortAscBubble))

    def test_mergesort_simple(self):
        toSortAsc = copy.deepcopy(SORT_DATA)
        toSortAsc.sort()
        toSortAscMerge = copy.deepcopy(SORT_DATA)
        QBubbleMergeSort.mergeSort(toSortAscMerge, 0, LAST_INDEX)
        self.assertEqual(set(toSortAsc), set(toSortAscMerge))


if __name__ == '__main__':
    unittest.main()
