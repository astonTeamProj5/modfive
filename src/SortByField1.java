import java.util.List;
import main.Record;

public class SortByField1 implements SortStrategy {
    @Override
    public List<Record> sort(List<Record> items) {
        System.out.println("TODO: sort by field1. Returning input as-is.");
        return items;
    }
}
