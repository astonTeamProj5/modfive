public class TestRunner {
    public static void main(String[] args) {
        int passed = 0;
        int failed = 0;

        if (runTest("RecordTests", RecordTests::run)) {
            passed++;
        } else {
            failed++;
        }

        if (runTest("FillStrategyTests", FillStrategyTests::run)) {
            passed++;
        } else {
            failed++;
        }

        if (runTest("SortStrategyTests", SortStrategyTests::run)) {
            passed++;
        } else {
            failed++;
        }

        System.out.println("Tests passed: " + passed + ", failed: " + failed);
        if (failed > 0) {
            System.exit(1);
        }
    }

    private static boolean runTest(String name, Runnable test) {
        try {
            test.run();
            System.out.println(name + " PASSED");
            return true;
        } catch (AssertionError | RuntimeException ex) {
            System.out.println(name + " FAILED: " + ex.getMessage());
            return false;
        }
    }
}
