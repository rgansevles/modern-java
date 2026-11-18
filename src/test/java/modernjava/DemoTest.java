package modernjava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import modernjava.domain.Employee;
import modernjava.domain.Failed;
import modernjava.domain.Ok;

/** Slide "Frameworks that need more than Java 8": JUnit 6 (Java 17) and Mockito 5 (Java 11). */
class DemoTest {

    @Test
    void describeHandlesEveryResult() {
        assertEquals("found Ada", SwitchPatternDemo.describe(new Ok(new Employee(1, "Ada", "Planning"))));
        assertEquals("not found", SwitchPatternDemo.describe(new Failed(404, "Not Found")));
        assertEquals("error 500", SwitchPatternDemo.describe(new Failed(500, "Internal Server Error")));
    }

    @ParameterizedTest
    @CsvSource({"12, 12", "twelve, 0", "'', 0"})
    void parseFallsBackToZero(String text, int expected) {
        assertEquals(expected, UnnamedDemo.parse(text));
    }

    @Test
    void sendAllUploadsInBatchesOf100() {
        @SuppressWarnings("unchecked")
        Consumer<List<String>> uploader = mock(Consumer.class);
        List<String> records = IntStream.rangeClosed(1, 250).mapToObj(i -> "record " + i).toList();

        new StreamsDemo(uploader).sendAll(records);

        verify(uploader, times(3)).accept(anyList());
    }

    @Test
    void mockitoMocksFinalClassesSuchAsRecords() {
        // Mockito 5: the inline mock maker is the default, so final classes and records can be mocked
        Employee employee = mock(Employee.class);
        when(employee.name()).thenReturn("Mocked");

        assertEquals("found Mocked", SwitchPatternDemo.describe(new Ok(employee)));
    }

    @Test
    void workdaysSkipTheWeekend() {
        assertEquals(false, StreamsDemo.isWorkday(LocalDate.of(2026, 11, 21)));   // Saturday
        assertEquals(true, StreamsDemo.isWorkday(LocalDate.of(2026, 11, 23)));    // Monday
    }

    @Test
    void listOfIsImmutable() {
        assertThrows(UnsupportedOperationException.class, () -> List.of("Mon").add("Tue"));
    }
}
