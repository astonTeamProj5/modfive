import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class FillRandomStrategy implements TextStrategy {
    private final Random random = new Random();

    private static final String[] MODELS = { "Volvo", "MAN", "Mercedes", "Scania", "Ikarus" };

    @Override
    public List<Record> fill(int length, Scanner scanner) {
        List<Record> items = new ArrayList<>();

        for (int i = 1; i <= length; i++) {
            String field1 = MODELS[random.nextInt(MODELS.length)];
            int field2 = random.nextInt(1000);
            int field3 = random.nextInt(1000) * 100;
            items.add(new Record.Builder()
                    .field1(field1)
                    .field2(field2)
                    .field3(field3)
                    .build());
        }
        return items;
    }
}
