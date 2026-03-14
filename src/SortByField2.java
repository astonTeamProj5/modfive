import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortByField2 implements SortStrategy {
    @Override
    public List<Record> sort(List<Record> items, SortDirection direction) {
        System.out.println("Sorting by field2 (" + direction + ").");
        List<Record> sorted = new ArrayList<>(items);
        // TODO:
        Comparator<Record> comparator = Comparator.comparingInt(Record::getField2);
        if (direction == SortDirection.DESC) {
            comparator = comparator.reversed();
        }
        sorted.sort(comparator);
        return sorted;
    }
}
