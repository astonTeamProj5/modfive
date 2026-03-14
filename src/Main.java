import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        try (SCANNER) {
            List<Record> items = createDemoData();
            boolean running = true;
            
            while (running) {
                printMenu();
                String input = SCANNER.nextLine().trim();
                
                switch (input) {
                    case "0" -> {
                        running = false;
                        System.out.println("Exiting...");
                    }
                    case "1" -> runStrategy(new SortByField1(), items, promptDirection());
                    case "2" -> runStrategy(new SortByField2(), items, promptDirection());
                    case "3" -> runStrategy(new SortByField3(), items, promptDirection());
                    default -> System.out.println("Invalid choice. Please enter 0, 1, 2, or 3.");
                }
            }
        }
    }

    private static void runStrategy(SortStrategy strategy, List<Record> items, SortDirection direction) {
        printItems("Before", items);
        List<Record> sorted = strategy.sort(items, direction);
        printItems("After", sorted);
        items.clear();
        items.addAll(sorted);
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Choose sort field:");
        System.out.println("1 - field1 (String)");
        System.out.println("2 - field2 (int)");
        System.out.println("3 - field3 (double)");
        System.out.println("0 - Exit");
        System.out.print("Your choice: ");
    }

    private static SortDirection promptDirection() {
        while (true) {
            System.out.println();
            System.out.println("Choose direction:");
            System.out.println("1 - Ascending");
            System.out.println("2 - Descending");
            System.out.print("Your choice: ");
            String input = SCANNER.nextLine().trim();
            if ("1".equals(input)) {
                return SortDirection.ASC;
            }
            if ("2".equals(input)) {
                return SortDirection.DESC;
            }
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }
    }

    private static void printItems(String title, List<Record> items) {
        System.out.println();
        System.out.println(title + " (" + items.size() + "):");
        if (items.isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        for (Record item : items) {
            System.out.println(item);
        }
    }

    private static List<Record> createDemoData() {
        List<Record> items = new ArrayList<>();
        items.add(new Record.Builder().field1("Alpha").field2(3).field3(10.5).build());
        items.add(new Record.Builder().field1("Gamma").field2(1).field3(7.2).build());
        items.add(new Record.Builder().field1("Beta").field2(2).field3(9.1).build());
        items.add(new Record.Builder().field1("Delta").field2(4).field3(5.4).build());
        return items;
    }
}
