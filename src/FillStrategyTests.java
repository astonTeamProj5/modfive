import java.util.List;
import java.util.Scanner;
import main.*;
import main.Record;//иначе придется в List<Record> писать <main.Record> и т.д.

public class FillStrategyTests {
    public static void run() {
        testFillStrategiesReturnExpectedSize();
    }

    private static void testFillStrategiesReturnExpectedSize() {
        TextStrategy[] strategies = new TextStrategy[] {
                new FillFromFileStrategy(),
                new FillRandomStrategy(),
                new FillManualStrategy()
        };

        for (TextStrategy strategy : strategies) {
            List<Record> items = strategy.fill(5, new Scanner(""));
            TestUtils.assertEqualsInt(5, items.size(), "fill() should return requested length");
        }
    }
}
