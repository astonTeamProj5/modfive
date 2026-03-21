import main.FillFromFileStrategy;
import main.FillManualStrategy;
import main.FillRandomStrategy;
import main.Record;
import main.TextStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        try (SCANNER) {
            Map<String, TextStrategy> strategies = new HashMap<>();
            strategies.put("1", new FillFromFileStrategy());
            strategies.put("2", new FillRandomStrategy());
            strategies.put("3", new FillManualStrategy());
            
            boolean running = true;
            
            while (running) {
                printMenu();
                String input = SCANNER.nextLine().trim();
                
                switch (input) {
                    case "0" -> {
                        running = false;
                        System.out.println("Exiting...");
                    }
                    case "1", "2", "3" -> {
                        int length = promptLength();
                        TextStrategy strategy = strategies.get(input);
                        List<Record> items = strategy.fill(length, SCANNER);
                    printItems(items);
                    }
                    default -> System.out.println("Invalid choice. Please enter 0, 1, 2, or 3.");
                }
            }
        }
    }

    private static int promptLength() {
        while (true) {
            System.out.print("Enter array length: ");
            String input = SCANNER.nextLine().trim();
            try {
                int length = Integer.parseInt(input);
                if (length <= 0) {
                    System.out.println("Length must be greater than 0.");
                } else {
                    return length;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Choose fill method:");
        System.out.println("1 - From file");
        System.out.println("2 - Random");
        System.out.println("3 - Manual");
        System.out.println("0 - Exit");
        System.out.print("Your choice: ");
    }

    private static void printItems(List<Record> items) {
        System.out.println();
        System.out.println("Result (" + items.size() + "):");
        if (items.isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        for (Record item : items) {
            System.out.println(item);
        }
    }
}
