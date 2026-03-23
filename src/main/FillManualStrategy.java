package main;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class FillManualStrategy implements TextStrategy {
    @Override
    public List<Record> fill(int length, Scanner scanner) {
        return IntStream.range(0, length)
                .mapToObj(i -> readRecord(i + 1, scanner))
                .collect(Collector.of(
                        CustomList::new,
                        CustomList::add,
                        (left, right) -> {
                            left.addAll(right);
                            return left;
                        }
                ));
    }

    private Record readRecord(int index, Scanner scanner) {
        System.out.println("Bus number " + index);

        String field1 = "";
        System.out.print("Enter model:");
        while (field1.isBlank()) {
            field1 = scanner.nextLine();
            if (field1.isBlank()) {
                System.out.print("Invalid input: model cannot be empty.\nPlease enter model again:");
            }
        }

        System.out.print("Enter bus serial number:");
        int field2 = 0;
        while (field2 <= 0) {
            try {
                field2 = Integer.parseInt(scanner.nextLine());
                if (field2 <= 0) {
                    System.out.print("Invalid number. Please enter number again:");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Please enter number again:");
            }
        }

        double field3 = -1.0;
        System.out.print("Enter mileage:");
        while (field3 < 0) {
            try {
                field3 = Double.parseDouble(scanner.nextLine());
                if (field3 < 0) {
                    System.out.print("Mileage cannot be less than 0. Please enter mileage again:");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Please enter mileage again:");
            }
        }

        return new Record.Builder()
                .field1(field1)
                .field2(field2)
                .field3(field3)
                .build();
    }
}
