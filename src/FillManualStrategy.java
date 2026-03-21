import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FillManualStrategy implements TextStrategy {
    @Override
    public List<Record> fill(int length, Scanner scanner) {
        List<Record> items = new ArrayList<>();

        for (int i = 1; i <= length; i++) {
            System.out.println("Bus number " + (i));

            String field1 = "";
            System.out.print("Enter model:");
            while(field1.isBlank()) {
                field1 = scanner.nextLine();
                if (field1.isBlank()) {
                    System.out.print("Invalid input: model cannot be empty.\nPlease enter model again:");
                }
            }

            System.out.print("Enter bus serial number:");
            int field2 = 0;
            while(field2 <= 0) {
                try {
                    field2 = Integer.parseInt(scanner.nextLine());
                    if(field2 <= 0){
                        System.out.print("Invalid number. Please enter number again:");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Invalid number. Please enter number again:");
                }
            }

            int field3 = -1;
            System.out.print("Enter mileage:");
            while(field3 < 0) {
                try {
                    field3 = Integer.parseInt(scanner.nextLine());
                    if (field3 < 0) {
                        System.out.print("Mileage cannot be less than 0. Please enter mileage again:");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Invalid number. Please enter mileage again:");
                }
            }

            items.add(new Record.Builder()
                    .field1(field1)
                    .field2(field2)
                    .field3(field3)
                    .build());
        }
        return items;
    }
}
