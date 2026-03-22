import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FillFromFileStrategy implements TextStrategy {
    @Override
    public List<Record> fill(int length, Scanner scanner) {
        System.out.println("TODO: load data from file. Using placeholder data.");
        List<Record> items = new ArrayList<>();
        for (int i = 1; i <= length; i++) {
            items.add(new Record.Builder()
                    .field1("FileItem" + i)
                    .field2(i)
                    .field3(i * 1.0)
                    .build());
        }
        return items;
    }
}
