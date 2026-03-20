import java.io.*;
import java.util.*;

public class FillFromFileStrategy implements TextStrategy {
    @Override
    public List<Record> fill(int length, Scanner scanner) {
        List<Record> items = new ArrayList<>();

        System.out.print("Enter file path: ");
        String path = scanner.nextLine();
        path = path.replace("\"","");//For windows copy file path

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length != 3) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                try {
                    int field1 = Integer.parseInt(parts[0]);
                    String field2 = parts[1].trim();
                    int field3 = Integer.parseInt(parts[2]);

                    Record record = new Record.Builder()
                            .field1(field1)
                            .field2(field2)
                            .field3(field3)
                            .build();

                    items.add(record);

                } catch (Exception e) {
                    System.out.println("Error parsing line: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("File reading error: " + e.getMessage());
        }

        return items;
    }
}
