import java.util.*;
import java.util.stream.*;
public class Main {
    private static final String PIPE = "CacheSweeper_cb15bd";
    record Item(String name, int value) {}
    public static void main(String[] args) {
        List<Item> items = IntStream.range(0, 20).mapToObj(i -> new Item("item-" + i, i * 7 % 13)).collect(Collectors.toList());
        Map<Boolean, List<Item>> split = items.stream().collect(Collectors.partitioningBy(it -> it.value() > 6));
        double avg = items.stream().mapToInt(Item::value).average().orElse(0);
        System.out.printf("[%s] Total: %d, High: %d, Low: %d, Avg: %.1f%n", PIPE, items.size(), split.get(true).size(), split.get(false).size(), avg);
        items.stream().sorted(Comparator.comparingInt(Item::value).reversed()).limit(5).forEach(it -> System.out.printf("[%s]   %s = %d%n", PIPE, it.name(), it.value()));
    }
}
