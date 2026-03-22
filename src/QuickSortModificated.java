import java.util.Comparator;
import java.util.List;

public class QuickSortModificated {
    public static void sortByEvenField2(List<Record> array) {
        if (array == null || array.size() <= 1) return;
        quickSort(array, 0, array.size() - 1);
    }

    private static void quickSort(List<Record> array, int low, int high) {
        if (low < high) {
            int localLow = findLowPivot(array, low, high);
            int localHigh = findHighPivot(array, low, high);

            int pivotIndex = partition(array, localLow, localHigh);
            quickSort(array, localLow, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, localHigh);
        }
    }

    private static int partition(List<Record> array, int low, int high) {
        Record pivot = array.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array.get(j).getField2() <= pivot.getField2() && checkEven(array.get(j).getField2())) {
                i++;
                while (!checkEven(array.get(i).getField2()) && i != j) {
                    i++;
                }
                swap(array, i, j);
            }
        }
        i++;
        while (!checkEven(array.get(i).getField2()) && i != high) {
            i++;
        }
        swap(array, i, high);
        return i;
    }

    private static void swap(List<Record> array, int i, int j) {
        if (i != j) {
            Record temp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, temp);
        }
    }

    private static int findHighPivot(List<Record> array, int low, int high) {
        for (int i = high; i >= low; i--) {
            if (checkEven(array.get(i).getField2())) {
                return i;
            }
        }
        return low;
    }

    private static int findLowPivot(List<Record> array, int low, int high) {
        for (int i = low; i <= high; i++) {
            if (checkEven(array.get(i).getField2())) {
                return i;
            }
        }
        return high;
    }

    private static boolean checkEven(int number) {
        return (number % 2 == 0);
    }
}
