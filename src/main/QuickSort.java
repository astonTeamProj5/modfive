package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuickSort {
    public static void sort(List<Record> array, Comparator<Record> comparator) {
        if (array == null || array.size() <= 1) return;
        quickSort(array, 0, array.size() - 1, comparator);
    }

    public static List<Record> sorted(List<Record> array, Comparator<Record> comparator) {
        if (array == null || array.size() <= 1) return array;

        List<Record> copy = new ArrayList<>(array);

        quickSort(copy, 0, array.size() - 1, comparator);

        return copy;
    }

    private static void quickSort(List<Record> array, int low, int high, Comparator<Record> comparator) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, comparator);
            quickSort(array, low, pivotIndex - 1, comparator);
            quickSort(array, pivotIndex + 1, high, comparator);
        }
    }

    private static int partition(List<Record> array, int low, int high, Comparator<Record> comparator) {
        Record pivot = array.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(array.get(j), pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(List<Record> array, int i, int j) {
        if (i != j) {
            Record temp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, temp);
        }
    }
}
