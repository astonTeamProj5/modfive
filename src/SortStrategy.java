import java.util.List;
import main.Record;

public interface SortStrategy {
    List<Record> sort(List<Record> items);
}
