import java.util.ArrayList;
import java.util.List;

public class SortStrategyTests {
    public static void run() {
        testSortStrategiesReturnList();
    }

    private static void testSortStrategiesReturnList() {
        List<Record> items = new ArrayList<>();
        items.add(new Record.Builder().field1("B").field2(2).field3(2.0).build());
        items.add(new Record.Builder().field1("A").field2(1).field3(3.0).build());

        SortStrategy[] strategies = new SortStrategy[] {
                new SortByField1(),
                new SortByField2(),
                new SortByField3()
        };

        for (SortStrategy strategy : strategies) {
            List<Record> result = strategy.sort(items);
            TestUtils.assertTrue(result != null, "sort() should not return null");
            TestUtils.assertEqualsInt(items.size(), result.size(), "sort() should keep list size");
        }
    }
}
