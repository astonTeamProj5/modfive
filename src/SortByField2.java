import java.util.List;

public class SortByField2 implements SortStrategy {
    @Override
    public List<Record> sort(List<Record> items) {
        System.out.println("TODO: sort by field2. Returning input as-is.");
        return items;
    }
}
