package tests;

import main.*;
import main.Record;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortStrategyTests {
    public static void run() {
        testSortStrategiesReturnList();
    }

    private static void testSortStrategiesReturnList() {
        List<Record> items = new ArrayList<>();
        items.add(new Record.Builder().field1("B").field2(2).field3(2.0).build());
        items.add(new Record.Builder().field1("A").field2(1).field3(3.0).build());

        List<Comparator<Record>> strategies = List.of(
                Record.byField1(),
                Record.byField2(),
                Record.byField3()
        );

        for (Comparator strategy : strategies) {
            List<Record> result = QuickSort.sorted(items, strategy);
            TestUtils.assertTrue(result != null, "sort() should not return null");
            TestUtils.assertEqualsInt(items.size(), result.size(), "sort() should keep list size");
        }
    }
}
