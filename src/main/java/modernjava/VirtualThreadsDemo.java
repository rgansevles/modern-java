package modernjava;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/** Slide "Also in the box": virtual threads (Java 21). */
public class VirtualThreadsDemo {

    public static void main(String[] args) {
        Instant start = Instant.now();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10_000).forEach(i -> executor.submit(() -> {
                Thread.sleep(Duration.ofSeconds(1));   // blocking I/O, simulated
                return i;
            }));
        }   // close() waits for all tasks
        System.out.println("10,000 blocking tasks of 1 second took "
            + Duration.between(start, Instant.now()).toMillis() + " ms");
    }
}
