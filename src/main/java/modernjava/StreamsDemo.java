package modernjava;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Gatherers;
import java.util.stream.IntStream;

import modernjava.domain.Employee;

/** Slide "Stream API and gatherers" (Java 9 / 16 / 24). */
public class StreamsDemo {

    private final Consumer<List<String>> uploader;

    StreamsDemo(Consumer<List<String>> uploader) {
        this.uploader = uploader;
    }

    static boolean isWorkday(LocalDate d) {
        return switch (d.getDayOfWeek()) {
            case SATURDAY, SUNDAY -> false;
            default -> true;
        };
    }

    void sendAll(List<String> records) {
        records.stream()                    // 24
            .gather(Gatherers.windowFixed(100))
            .forEach(uploader);
    }

    public static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee(1, "Ada", "Planning"),
            new Employee(2, "Linus", "Planning"),
            new Employee(3, "Grace", "HR"));

        var ids = employees.stream()
            .map(Employee::id)
            .toList();                      // 16
        System.out.println("ids: " + ids);

        LocalDate start = LocalDate.of(2026, 11, 1);
        LocalDate end = LocalDate.of(2026, 12, 1);
        long n = start.datesUntil(end)      // 9
            .filter(StreamsDemo::isWorkday)
            .count();
        System.out.println("workdays in November 2026: " + n);

        List<String> records = IntStream.rangeClosed(1, 250).mapToObj(i -> "record " + i).toList();
        new StreamsDemo(batch -> System.out.println("upload batch of " + batch.size()
                + " (" + batch.getFirst() + " .. " + batch.getLast() + ")"))
            .sendAll(records);
    }
}
