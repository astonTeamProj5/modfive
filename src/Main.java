import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        try (SCANNER) {
            Map<String, TextStrategy> fillStrategies = new HashMap<>();
            fillStrategies.put("1", new FillFromFileStrategy());
            fillStrategies.put("2", new FillRandomStrategy());
            fillStrategies.put("3", new FillManualStrategy());

            Map<String, SortStrategy> sortStrategies = new HashMap<>();
            sortStrategies.put("1", new SortByField1());
            sortStrategies.put("2", new SortByField2());
            sortStrategies.put("3", new SortByField3());

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
                        TextStrategy fillStrategy = fillStrategies.get(input);
                        List<Record> items = fillStrategy.fill(length, SCANNER);

                        SortStrategy sortStrategy = promptSortStrategy(sortStrategies);
                        if (sortStrategy == null) {
                            running = false;
                        } else {
                            List<Record> sorted = sortStrategy.sort(items);
                            printItems(sorted);
                        }
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

    private static SortStrategy promptSortStrategy(Map<String, SortStrategy> sortStrategies) {
        while (true) {
            printSortMenu();
            String input = SCANNER.nextLine().trim();
            if ("0".equals(input)) {
                return null;
            }
            SortStrategy strategy = sortStrategies.get(input);
            if (strategy != null) {
                return strategy;
            }
            System.out.println("Invalid choice. Please enter 0, 1, 2, or 3.");
        }
    }

    private static void printSortMenu() {
        System.out.println();
        System.out.println("Choose sort field:");
        System.out.println("1 - field1 (String)");
        System.out.println("2 - field2 (int)");
        System.out.println("3 - field3 (double)");
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
