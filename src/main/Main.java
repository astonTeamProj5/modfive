package main;

import java.util.*;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        try (SCANNER) {
            Map<String, TextStrategy> fillStrategies = new HashMap<>();
            fillStrategies.put("1", new FillFromFileStrategy());
            fillStrategies.put("2", new FillRandomStrategy());
            fillStrategies.put("3", new FillManualStrategy());

            Map<String, Comparator<Record>> sortStrategies = new HashMap<>();
            sortStrategies.put("1", Record.byField1());
            sortStrategies.put("2", Record.byField2());
            sortStrategies.put("3", Record.byField3());

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

                        Comparator<Record> sortStrategy = promptSortStrategy(sortStrategies);
                        if (sortStrategy == null) {
                            running = false;
                        } else {
                            printItems(items);
                            List<Record> sorted = QuickSort.sorted(items, sortStrategy);
                            printItems(sorted);

                            System.out.print("Count occurrences of an element in the result? (y/N): ");
                            String doCount = SCANNER.nextLine().trim();
                            if ("y".equalsIgnoreCase(doCount)) {
                                if (sorted.isEmpty()) {
                                    System.out.println("List is empty.");
                                } else {
                                    System.out.print("Enter index of element to count (1-" + sorted.size() + "): ");
                                    String idxInput = SCANNER.nextLine().trim();
                                    try {
                                        int idx = Integer.parseInt(idxInput);
                                        if (idx < 1 || idx > sorted.size()) {
                                            System.out.println("Index out of range.");
                                        } else {
                                            Record target = sorted.get(idx - 1);
                                            long count = parallelCountOccurrences(sorted, target);
                                            System.out.println("Occurrences of " + target + ": " + count);
                                        }
                                    } catch (NumberFormatException ex) {
                                        System.out.println("Invalid number.");
                                    }
                                }
                            }
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

    private static Comparator<Record> promptSortStrategy(Map<String, Comparator<Record>> sortStrategies) {
        while (true) {
            printSortMenu();
            String input = SCANNER.nextLine().trim();
            if ("0".equals(input)) {
                return null;
            }
            Comparator<Record> strategy = sortStrategies.get(input);
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

    private static <T> long parallelCountOccurrences(List<T> list, T target) {
        if (list == null || list.isEmpty()) {
            return 0L;
        }
        if (target == null) {
            return list.parallelStream().filter(Objects::isNull).count();
        } else {
            return list.parallelStream().filter(target::equals).count();
        }
    }
}
