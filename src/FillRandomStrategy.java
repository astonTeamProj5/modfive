import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class FillRandomStrategy implements TextStrategy {
    private final Random random = new Random();

    @Override
    public List<Record> fill(int length, Scanner scanner) {
        System.out.println("TODO: generate real random data rules. Using simple random placeholders.");
        List<Record> items = new ArrayList<>();
        for (int i = 1; i <= length; i++) {
            int field2 = random.nextInt(100);
            double field3 = random.nextDouble() * 100.0;
            items.add(new Record.Builder()
                    .field1("RandomItem" + i)
                    .field2(field2)
                    .field3(field3)
                    .build());
        }
        return items;
    }
}
