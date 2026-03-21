import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FillTestRunner {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args){
        Map<String, TextStrategy> strategies = new HashMap<>();
        strategies.put("1", new FillFromFileStrategy());
        strategies.put("2", new FillRandomStrategy());
        strategies.put("3", new FillManualStrategy());

        runFileFillTests(strategies.get("1"));

        //runRandomFillTests(strategies.get("2"));

        //runManualFillTests(strategies.get("3"));

    }

    private static void testStrategy(TextStrategy strategy, int length, Scanner scanner) {
        List<Record> items = strategy.fill(length, scanner);

        if(items.isEmpty()){
            System.out.println("Test results empty");
            return;
        }
        System.out.println("Test results with " + length + " items:");
        for (Record item : items) {
            System.out.println(item);
        }
    }

    private static void runRandomFillTests(TextStrategy strategy) {
        testStrategy(strategy, 0, SCANNER);
        testStrategy(strategy, 10, SCANNER);
        testStrategy(strategy, -10, SCANNER);
        testStrategy(strategy, 1000, SCANNER);
    }
    
    private static void runManualFillTests(TextStrategy strategy){
        Scanner scanner = new Scanner("""
                1
                Volvo
                100
                2
                MAN
                200
                """);
        testStrategy(strategy, 2, scanner);
        scanner = new Scanner("""
                
                1
                
                volvo
                
                100
                """);
        testStrategy(strategy, 1, scanner);
        
        scanner = new Scanner("""
                -1
                10
                volvo
                -1000
                100
                """);
        testStrategy(strategy, 1, scanner);
        scanner = new Scanner("""
                """);
        testStrategy(strategy, -10, scanner);
    }

    private static void runFileFillTests(TextStrategy strategy){
        Scanner scanner = new Scanner("testFileFill.txt");
        testStrategy(strategy, 2, scanner);
        scanner = new Scanner("testFileFill.txt");
        testStrategy(strategy, 1, scanner);
        scanner = new Scanner("testFileFill.txt");
        testStrategy(strategy, 0, scanner);
        scanner = new Scanner("testFileFill.txt");
        testStrategy(strategy, -10, scanner);
        scanner = new Scanner("testFileFill.txt");
        testStrategy(strategy, 6, scanner);
    }
}
