package main;

import java.io.*;
import java.util.*;

public class FillFromFileStrategy implements TextStrategy {
    @Override
    public List<Record> fill(int length, Scanner scanner) {
        List<Record> items = new ArrayList<>();

        System.out.print("Enter file path: ");
        if(!scanner.hasNext()){
            System.out.println("No input provided");
            return new ArrayList<>();
        }

        String path = scanner.nextLine();
        path = path.replace("\"","");//Для кнопки "копировать как путь" винды
        System.out.println();//переносим строку для красивого вывода
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            for (int i = 0; i < length; i++) {
                String line = reader.readLine();
                if(line == null){
                    System.out.println("Unexpected end of file.");
                    break;
                }
                String[] parts = line.split(",");

                if (parts.length != 3) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                try {
                    String field1 = parts[0].trim();
                    int field2 = Integer.parseInt(parts[1]);
                    if(field2 <= 0){
                        System.out.println("Invalid number of bus " + field2);
                        continue;
                    }
                    double field3 = Double.parseDouble(parts[2]);
                    if(field3 < 0){
                        System.out.println("Invalid mileage " + field3);
                        continue;
                    }

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
