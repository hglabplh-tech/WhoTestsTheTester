package io.examples.sort;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntFunction;

import static io.examples.sort.QBubbleMerge.SortDirection.ASC;
import static io.examples.sort.QBubbleMerge.SortDirection.DESC;
import static io.examples.sort.SortData.*;
import static java.util.Arrays.parallelSort;

public class QBubbleMergeTest {


    private QBubbleMergeTest() {

    }

    @BeforeAll
    public static void beforeAll() {

    }


    @BeforeEach
    public void beforeEach() {

    }

    @Test
    public void testQuickSortSimple() {
        Integer[] arrayToSort = newArray();
        Integer[] arrayToSortComp = newArray();
        Arrays.<Integer>parallelSort(arrayToSortComp, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        QBubbleMerge.quickSort(arrayToSort, ASC, 0, LAST_INDEX);

        arrayToSort = newArray();
        arrayToSortComp = newArray();
        Arrays.<Integer>parallelSort(arrayToSortComp, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        QBubbleMerge.quickSort(arrayToSort, DESC, 0, LAST_INDEX);

    }

    @Test
    public void testBubbleSortSimple() {

    }

    @Test
    public void testMergeSortSimple() {

    }

    private  Integer[] newArray() {
        Integer [] arrayToSort = new Integer[LAST_INDEX + 1];
        System.arraycopy(SORT_DATA, 0, arrayToSort, 0, LAST_INDEX + 1);
        return arrayToSort;
    }


}
