package main;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class FillRandomStrategy implements TextStrategy {
    private final Random random = new Random();

    private static final String[] MODELS = { "Volvo", "MAN", "Mercedes", "Scania", "Ikarus" };

    // Fill uses a stream:
    // 1) IntStream.range creates indices from 0 to length-1.
    // 2) mapToObj builds a Record for each index.
    // 3) collect gathers results into CustomList (custom collection).
    @Override
    public List<Record> fill(int length, Scanner scanner) {
        return IntStream.range(0, length)
                // for each i we create a random Record
                .mapToObj(i -> {
                    String field1 = MODELS[random.nextInt(MODELS.length)];
                    int field2 = random.nextInt(1000);
                    double field3 = random.nextDouble(1000) * 100;
                    return new Record.Builder()
                            .field1(field1)
                            .field2(field2)
                            .field3(field3)
                            .build();
                })
                // Detailed collector explanation:
                // CustomList::new   - supplier: creates empty CustomList result
                // CustomList::add   - accumulator: adds one Record into current result
                // (left, right)     - combiner: merges partial results for parallel stream
                // left.addAll(right) returns left as the merged result
                .collect(Collector.of(
                        CustomList::new,
                        CustomList::add,
                        (left, right) -> {
                            left.addAll(right);
                            return left;
                        }
                ));
    }
}
