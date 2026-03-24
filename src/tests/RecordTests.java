package tests;

import main.*;
import main.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordTests {
    public static void run() {
        testBuilderAndGetters();
        testComparators();
    }

    private static void testBuilderAndGetters() {
        Record record = new Record.Builder()
                .field1("A")
                .field2(10)
                .field3(2.5)
                .build();

        TestUtils.assertEquals("A", record.getField1(), "field1 should match");
        TestUtils.assertEqualsInt(10, record.getField2(), "field2 should match");
        TestUtils.assertEqualsDouble(2.5, record.getField3(), 0.0001, "field3 should match");
    }

    private static void testComparators() {
        List<Record> items = new ArrayList<>();
        items.add(new Record.Builder().field1("B").field2(2).field3(2.0).build());
        items.add(new Record.Builder().field1("A").field2(1).field3(3.0).build());

        items.sort(Record.BY_FIELD1);
        TestUtils.assertEquals("A", items.get(0).getField1(), "byField1 should sort ascending");

        items.sort(Record.BY_FIELD2);
        TestUtils.assertEqualsInt(1, items.get(0).getField2(), "byField2 should sort ascending");

        items.sort(Record.BY_FIELD3);
        TestUtils.assertEqualsDouble(2.0, items.get(0).getField3(), 0.0001, "byField3 should sort ascending");
    }
}
