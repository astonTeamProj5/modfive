import java.util.Objects;

public final class TestUtils {
    private TestUtils() {
    }

    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    public static void assertEquals(Object expected, Object actual, String message) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError(message + " Expected: " + expected + ", actual: " + actual);
        }
    }

    public static void assertEqualsInt(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + " Expected: " + expected + ", actual: " + actual);
        }
    }

    public static void assertEqualsDouble(double expected, double actual, double eps, String message) {
        if (Math.abs(expected - actual) > eps) {
            throw new AssertionError(message + " Expected: " + expected + ", actual: " + actual);
        }
    }
}
