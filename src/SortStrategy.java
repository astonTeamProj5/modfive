import java.util.List;

public interface SortStrategy {
    List<Record> sort(List<Record> items);
}
